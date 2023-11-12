package ru.otus.sotset.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import ru.otus.sotset.service.post.PostWarmupService

@Controller
class HomeController(
    val postWarmupService: PostWarmupService
) {

    @GetMapping("/")
    fun home(): String = "redirect:swagger-ui.html"

    @ResponseBody
    @GetMapping("/warmup")
    fun startWarmup() = postWarmupService.warmup()
}
