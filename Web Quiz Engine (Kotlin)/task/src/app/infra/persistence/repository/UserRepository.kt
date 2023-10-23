package app.infra.persistence.repository

import app.domain.model.Solution
import app.domain.model.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<User, Int> {
    fun findByEmail(email: String): User?
}