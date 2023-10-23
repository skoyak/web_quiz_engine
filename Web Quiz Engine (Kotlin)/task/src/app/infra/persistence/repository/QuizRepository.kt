package app.infra.persistence.repository

import app.domain.model.Quiz
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface QuizRepository: CrudRepository<Quiz, Int> {

    @Query(value = "SELECT * FROM QUIZ", nativeQuery = true)
    fun search(pageable: Pageable): List<Quiz>

}