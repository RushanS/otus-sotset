package ru.otus.sotset.web.controller

import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.sotset.service.FriendService

@RestController
@RequestMapping("\${api.base-path:}")
class FriendApiController(
    val friendService: FriendService
) {

    @PutMapping("/friend/delete/{userId}")
    fun deleteFriend(@Parameter(required = true) @PathVariable userId: String) {
        friendService.deleteFriend(userId)
    }

    @PutMapping("/friend/set/{userId}")
    fun setFriend(@Parameter(required = true) @PathVariable userId: String) {
        friendService.setFriend(userId)
    }

}
