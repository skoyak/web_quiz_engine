package app.infra.rest.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateQuizRequest(
    @field: NotBlank
    val title: String,
    @field: NotBlank
    val text: String,
    @field: Size(min = 2)
    val options: List<String>,
    val answer: List<Int?> = emptyList()
)