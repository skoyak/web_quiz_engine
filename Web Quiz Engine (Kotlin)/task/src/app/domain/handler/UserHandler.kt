package app.domain.handler

import app.domain.exception.UserAlreadyExistsException
import app.domain.model.User
import app.domain.port.UserPort
import app.domain.usecase.AddUserUsecase
import app.domain.usecase.GetUserUsecase
import org.springframework.security.crypto.password.PasswordEncoder

class UserHandler(
    private val userPort: UserPort,
    private val encoder: PasswordEncoder
) : AddUserUsecase, GetUserUsecase {
    override fun addUser(user: User) {

        check(userPort.getUser(email = user.email) == null) { throw UserAlreadyExistsException() }

        userPort.addUser(user = user.copy(password = encoder.encode(user.password)))
    }

    override fun getUser(email: String): User {
        return checkNotNull(userPort.getUser(email = email)) { "User doesn't exist" }
    }
}