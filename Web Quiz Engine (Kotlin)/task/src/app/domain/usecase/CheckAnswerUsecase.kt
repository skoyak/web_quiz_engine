package app.domain.usecase

import app.domain.model.QuizResult
import app.domain.model.User


interface CheckAnswerUsecase {
    fun checkAnswer(user: User, quizId: Int, answers: List<Int?>): QuizResult
}