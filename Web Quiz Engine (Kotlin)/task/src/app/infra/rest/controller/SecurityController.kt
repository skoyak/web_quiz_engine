package app.infra.rest.controller

import app.domain.handler.UserHandler
import app.domain.model.User
import app.infra.rest.dto.RegisterRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SecurityController(
    private val userHandler: UserHandler
) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid request: RegisterRequest): ResponseEntity<HttpStatus> {
        userHandler.addUser(user = request.toUser())
        return ResponseEntity(HttpStatus.OK)
    }
}