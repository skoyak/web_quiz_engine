package app.infra.persistence.adapter

import app.config.UserAdapter
import app.domain.model.User
import app.domain.port.UserPort
import app.infra.persistence.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class UserPersistenceAdapter(
    private val repository: UserRepository,
): UserDetailsService, UserPort {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = checkNotNull(repository.findByEmail(email = email)){"User doesn't exist"}

        return UserAdapter(user = user)
    }

    override fun addUser(user: User) {
        repository.save(user)
    }

    override fun updateUser(user: User) {
        repository.save(user)
    }

    override fun getUser(email: String): User? {
        return repository.findByEmail(email = email)
    }
}