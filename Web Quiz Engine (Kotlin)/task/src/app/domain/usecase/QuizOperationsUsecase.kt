package app.domain.usecase

import app.domain.model.Quiz

interface QuizOperationsUsecase{

    fun addQuiz(quiz: Quiz): Quiz

    fun removeQuiz(quizId: Int, userId: Int)
}