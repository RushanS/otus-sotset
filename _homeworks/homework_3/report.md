# Отчет по домашнему заданию 3 "Полусинхронная репликация"

#### Table of contents

1. [Настраиваем асинхронную репликацию](#async-repl)
    - [Настройка мастера](#async-repl-master)
    - [Настройка слейва](#async-repl-slave)
2. [Переносим чтение на слейв](#read-slave)


<a id="async-repl"></a>
## Настраиваем асинхронную репликацию

Цель настроить окружение с помощью [docker-compose.yml](..%2F..%2F_docker%2Fdocker-compose.yml), чтобы одной кнопкой (или командой) можно было всё поднять без дополнительных действий и необходимости лезть внутрь контейнеров для их настройки.


<a id="async-repl-master"></a>
### Настройка мастера

Добавляем postgresql мастер-ноду в docker-compose.yml
```yaml
  postgres-master:
    image: postgres
    restart: unless-stopped
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
      POSTGRES_DB: sotset
    command: |
      postgres 
      -c wal_level=replica 
      -c max_wal_senders=10 
      -c hba_file=/etc/postgresql/pg_hba.conf
    ports:
      - "5432:5432"
    volumes:
      - ./postgres/master_pg_hba.conf:/etc/postgresql/pg_hba.conf
      - ./postgres/db-init:/docker-entrypoint-initdb.d/
```
Здесь:
 - В секции environment задаём переменные окружения:
   - **POSTGRES_USER** - имя пользователя бд, который создастся при старте контейнера.
   - **POSTGRES_PASSWORD** - пароль для пользователя БД
   - **POSTGRES_DB** - имя БД, которая создастся при старте контейнера.
 - В секции command указываем команду для запуска postgres и передаём некоторые свойства конфигурации. Эти параметры конфигурации можно было бы передать также через файл **postgresql.conf**, но в моём случае меньше гемора через аргументы команды запуска.
   - **wal_level** - какие данные будут записываться в "журнал предзаписи (WAL)" для репликации. При значении **replica** в журнал будут записываться данные, необходимые для бинарной (нелогической) репликации.
   - **max_wal_senders** - максимальное число одновременных подключений ведомых серверов, т.е. максимальное кол-во реплик
   - **hba_file** - путь к файлу конфигурации host-based authentication (hba). Нам нужно будет добавить в этот файл доступ для реплик. По умолчанию, этот файл лежит в папке `/var/lib/postgresql/data`. Но мы не сможем подложить этот файл в папку по умолчанию, так как эта папка при старте контейнера должна быть пустой, чтобы postgres записал туда свои данные при инициализации.
 - В секции volumes пропишем маппинг файлов и папок в локальной файловой системе на файловую систему контейнеров.
   - [./postgres/master_pg_hba.conf](..%2F..%2F_docker%2Fpostgres%2Fmaster_pg_hba.conf) файл hba с необходимой мне конфигурацией. Обязательно мапится на путь, который мы указали в параметре запуска `hba_file`.
   - [./postgres/db-init](..%2F..%2F_docker%2Fpostgres%2Fdb-init) содержит SQL-скрипты, которые будут выполнены при старте контейнера. Мапится на `/docker-entrypoint-initdb.d/`


Создадим пользователя для репликации на мастер-ноде. В папку с SQL-скриптами [./postgres/db-init](..%2F..%2F_docker%2Fpostgres%2Fdb-init) добавим файл [replication-user.sql](..%2F..%2F_docker%2Fpostgres%2Fdb-init%2Freplication-user.sql) со следующим кодом:
```postgresql
CREATE USER replicator WITH REPLICATION ENCRYPTED PASSWORD 'replicator_password';
```
Этот код создаст пользователя с именем *replicator* и паролем *replicator_password* и с правами для репликации.


Добавим доступ в [master_pg_hba.conf](..%2F..%2F_docker%2Fpostgres%2Fmaster_pg_hba.conf).
Все строки в этом файле созданы по умолчанию, дописываю туда ещё одну строку:
```
host    replication    replicator    0.0.0.0/0    trust
```
Эта строка разрешает доступ пользователю replicator доступ к репликации с любых ip-адресов.


<a id="async-repl-slave"></a>
### Настройка слейва

Теперь добавим слейв-ноду:
```yaml
  postgres-slave-1:
     image: postgres
     restart: unless-stopped
     user: postgres
     depends_on:
        - postgres-master
     environment:
        PGUSER: replicator
        PGPASSWORD: replicator_password
     command: |
        bash -c "
        pg_basebackup --pgdata=/var/lib/postgresql/data -R --host=postgres-master --port=5432 --no-password
        chmod 0700 /var/lib/postgresql/data
        postgres
        "
     ports:
        - "5433:5432"
```
Здесь:
- Секция **depends_on** говорит нам, что контейнер должен запускаться после запуска контейнера мастера.
- В секции environment задаём переменные окружения **PGUSER** и **PGPASSWORD**. Это имя и пароль пользователя, с которыми утилита **pg_basebackup** будет подключаться к мастеру и создавать его резервную копию.
- В секции command напишем небольшой bash-скрипт из трех команд. Первая команда **pg_basebackup** получит копию мастера. Вторая команда *chmod* поправит права папки data. Третья команда *postgres* просто запустит БД. В команду **pg_basebackup** передаём следующие параметры:
  - `--pgdata` - целевой каталог, куда будет записана копия. Указываем папку data postgres-а.
  - `-R` - создать файл standby.signal и добавить параметры конфигурации в файл postgresql.auto.conf в целевом каталоге. Это упрощает настройку ведомого сервера при восстановлении этой копии. В файл postgresql.auto.conf будут записаны параметры соединения, так что впоследствии при потоковой репликации будут использоваться те же параметры.
  - `--host` - hostname сервера, с которого делаем копию, в данном случае имя нашего мастера.
  - `--port` - порт для подключения к серверу, с которого делаем копию.
  - `--no-password` - не выдавать пользователю запрос на ввод пароля. Будет использоваться пароль из переменной окружения.

То есть, при старте котейнера слейва мы с помощью утилиты *pg_basebackup* получаем копию мастера с дополнительными настройками для репликации, и записываем её в папку *data* postgres-a. И потом просто запускаем сервер postgres.


Мастер и слейв настроены, запускаем проверяем.


<a id="read-slave"></a>
## Переносим чтение на слейв

Добавляем в файл конфигурации настройки для подключения к двум БД:
```yaml

  master-datasource:
    driver-class-name: org.postgresql.Driver
    jdbc-url: jdbc:postgresql://localhost:5432/sotset
    username: postgres
    password: password
    platform: postgresql
    maximum-pool-size: 90

  slave-datasource:
    driver-class-name: org.postgresql.Driver
    jdbc-url: jdbc:postgresql://localhost:5433/sotset
    username: postgres
    password: password
    platform: postgresql
    maximum-pool-size: 90

```

В docker-compose так же переопределяем URL-ы для подключения для мастера и слейва, используя внутренние адреса докера:
```yaml
    environment:
      - SPRING_MASTER_DATASOURCE_JDBC_URL=jdbc:postgresql://postgres-master:5432/sotset
      - SPRING_SLAVE_DATASOURCE_JDBC_URL=jdbc:postgresql://postgres-slave-1:5432/sotset
```

Добавляем в spring-конфигурацию приложения два разных dataSource и два jdbcTemplate (spring-бины с разными именами).
По умолчанию (@Primary) будет использоваться подключение к мастеру:
```kotlin
    @Bean("masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.master-datasource")
    fun masterDataSource(): HikariDataSource = HikariDataSource()

    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.slave-datasource")
    fun slaveDataSource(): HikariDataSource = HikariDataSource()

    @Bean("masterJdbcTemplate")
    @Primary
    fun masterJdbcTemplate() = NamedParameterJdbcTemplate(masterDataSource())

    @Bean("slaveJdbcTemplate")
    fun slaveJdbcTemplate() = NamedParameterJdbcTemplate(slaveDataSource())
```

Потом в коде, который делает запросы, будем использовать два разных jdbcTemplate, slave для чтения, master для записи:
```kotlin
    @Qualifier("masterJdbcTemplate") private val masterJdbcTemplate: NamedParameterJdbcTemplate,
    @Qualifier("slaveJdbcTemplate") private val slaveJdbcTemplate: NamedParameterJdbcTemplate,
```

-----------------------
https://medium.com/@eremeykin/how-to-setup-single-primary-postgresql-replication-with-docker-compose-98c48f233bbf
