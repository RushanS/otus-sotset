package ru.otus.sotset.service

import org.springframework.stereotype.Service
import ru.otus.sotset.BadUserInputException
import ru.otus.sotset.model.User
import ru.otus.sotset.repository.FriendRepository
import ru.otus.sotset.repository.UserRepository
import ru.otus.sotset.service.auth.CurrentUser

@Service
class FriendService(
    val currentUser: CurrentUser,
    val userRepository: UserRepository,
    val friendRepository: FriendRepository
) {

    fun setFriend(userId: String) {
        val friendOwner = currentUser.user
        val friend = getUser(userId)
        if (friendOwner.id == friend.id) {
            throw BadUserInputException("Cannot be friends with yourself.")
        }
        friendRepository.setFriend(friendOwner, friend)
    }

    fun deleteFriend(userId: String) {
        val friendOwner = currentUser.user
        val friend = getUser(userId)
        friendRepository.removeFriend(friendOwner, friend)
    }

    private fun getUser(id: String): User = userRepository.find(id.toUUID())
        ?: throw BadUserInputException("Cannot edit friends. User with given id is not exists.")

}
