package app.domain.port

import app.domain.model.Question
import app.domain.model.Quiz
import app.domain.model.Solution
import app.domain.model.User
import org.springframework.data.domain.Pageable

interface QuizPort {
    fun getQuiz(id: Int): Quiz?

    fun addQuiz(quiz: Quiz): Quiz

    fun getAllQuizzes(pageable: Pageable): List<Quiz?>

    fun getSolvedQuizzes(userId: Int, pageable: Pageable): List<Solution>?

    fun countQuizzes(): Long

    fun removeQuiz(id: Int)

    fun saveSolution(solution: Solution): Solution

    fun removeSolutions(quizId: Int)
}