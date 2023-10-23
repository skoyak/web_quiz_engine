package app.domain.model

import app.infra.rest.dto.AnswerQuizResponse

data class QuizResult(val success: Boolean, val feedback: String)

fun QuizResult.toQuizResponse(): AnswerQuizResponse {
    return AnswerQuizResponse(success = this.success, feedback = this.feedback)
}
