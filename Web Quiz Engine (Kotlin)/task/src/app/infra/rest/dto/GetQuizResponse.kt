package app.infra.rest.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import app.domain.model.Question
import app.domain.model.Quiz

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GetQuizResponse(
    val id: Int?,
    val title: String,
    val text: String,
    val options: List<String>,
)