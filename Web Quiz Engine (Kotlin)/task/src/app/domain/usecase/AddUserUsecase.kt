package app.domain.usecase

import app.domain.model.User

interface GetUserUsecase {
    fun getUser(email: String): User?
}