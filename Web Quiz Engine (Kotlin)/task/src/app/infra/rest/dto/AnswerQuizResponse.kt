package app.infra.rest.dto

import app.domain.model.QuizResult
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AnswerQuizResponse(val success: Boolean, val feedback: String)