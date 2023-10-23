package app.infra.rest.dto

import java.time.Instant

data class GetSolvedQuizResponse(val id: Int, val completedAt: Instant)
