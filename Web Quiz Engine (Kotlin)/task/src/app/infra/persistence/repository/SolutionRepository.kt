package app.infra.persistence.repository

import app.domain.model.Quiz
import app.domain.model.Solution
import app.domain.model.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface SolutionRepository: CrudRepository<Solution, Int>{
    @Query("SELECT q FROM Solution q WHERE q.user.id = :id")
    fun findSolutions(@Param("id") id: Int, pageable: Pageable): List<Solution>?

    @Transactional
    @Modifying
    @Query("DELETE FROM Solution q WHERE q.quiz.id = :quizId")
    fun deleteByQuizId(@Param("quizId") quizId: Int)
}