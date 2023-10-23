package app.domain.usecase

import app.domain.model.Quiz
import app.domain.model.Solution
import app.domain.model.User
import org.springframework.data.domain.Pageable

interface GetQuizUsecase {

    fun getQuiz(id: Int): Quiz

    fun getAllQuizzesInPage(pageable: Pageable): List<Quiz?>

    fun getSolvedQuizzesInPage(userId: Int, pageable: Pageable): List<Solution>?

    fun countAllQuizzes(): Long

}