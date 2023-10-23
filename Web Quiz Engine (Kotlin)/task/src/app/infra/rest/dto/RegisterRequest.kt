package app.infra.rest.dto

import app.domain.model.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class RegisterRequest(

    @field:Email(regexp = ".+[@].+[\\.].+")
    val email: String,

    @field:Size(min = 5)
    val password: String
) {
    fun toUser(): User {
        return User(
            email = this.email,
            password = this.password,
            authorities = listOf("ROLE_USER"),
            solutions = emptyList(),
        )
    }
}
