package app.infra.rest.dto

import app.domain.model.Quiz
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.annotation.processing.Generated

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)

class CreateQuizResponse(val id: Int?, val title: String, val text: String, val options: List<String>)
