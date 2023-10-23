package app.domain.usecase

import app.domain.model.User

interface AddUserUsecase {
    fun addUser(user: User)
}