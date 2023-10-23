package app.domain.port

import app.domain.model.User

interface UserPort{
    fun addUser(user: User)

    fun updateUser(user: User)

    fun getUser(email: String): User?
}