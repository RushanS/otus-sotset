FROM gradle:8-jdk17
ADD . /code
WORKDIR /code
RUN gradle clean build -x test
CMD gradle bootRun
