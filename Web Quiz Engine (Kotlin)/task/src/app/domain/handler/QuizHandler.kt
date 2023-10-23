package app.domain.handler

import app.domain.exception.QuizNotFoundException
import app.domain.exception.UnauthorizedQuizOperation
import app.domain.model.Quiz
import app.domain.model.QuizResult
import app.domain.model.Solution
import app.domain.model.User
import app.domain.port.QuizPort
import app.domain.port.UserPort
import app.domain.usecase.QuizOperationsUsecase
import app.domain.usecase.CheckAnswerUsecase
import app.domain.usecase.GetQuizUsecase
import org.springframework.data.domain.Pageable
import java.time.Instant

class QuizHandler(
    private val quizPort: QuizPort,
) : GetQuizUsecase, CheckAnswerUsecase, QuizOperationsUsecase {
    override fun getQuiz(id: Int): Quiz  {

        return checkNotNull(quizPort.getQuiz(id = id)){ throw QuizNotFoundException() }
    }

    override fun getAllQuizzesInPage(pageable: Pageable): List<Quiz?> {

        return quizPort.getAllQuizzes(pageable = pageable)
    }

    override fun getSolvedQuizzesInPage(userId: Int, pageable: Pageable): List<Solution>? {

        return quizPort.getSolvedQuizzes(userId = userId, pageable = pageable)
    }

    override fun countAllQuizzes(): Long {
        return quizPort.countQuizzes()
    }

    override fun checkAnswer(user: User, quizId: Int, answers: List<Int?>): QuizResult {

        val quiz = checkNotNull(quizPort.getQuiz(id = quizId)){ throw QuizNotFoundException() }

        val success = quiz.answers.toSet() == answers.toSet()

        if (success) {
            println("Correct answer. Saving...")
            val solution = Solution(quiz = quiz, user = user, answeredAt = Instant.now())
            quizPort.saveSolution(solution = solution)
        }

        val feedback = when (success) {
            true -> "Congratulations, you're right!"
            false -> "Wrong answer! Please, try again."
        }

        return QuizResult(success = success, feedback = feedback)
    }

    override fun addQuiz(quiz: Quiz): Quiz {
        return quizPort.addQuiz(quiz = quiz)
    }

    override fun removeQuiz(quizId : Int, userId: Int) {

        val quiz = checkNotNull(quizPort.getQuiz(id = quizId)){ throw QuizNotFoundException() }
        check(quiz.user.id == userId){ throw UnauthorizedQuizOperation()}

        quizPort.removeSolutions(quizId = quizId)
        quizPort.removeQuiz(id = quizId)
    }
}