package app.domain.model

import app.infra.rest.dto.GetQuizResponse
import jakarta.persistence.*

@Entity
class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Embedded
    val question: Question,

    @ElementCollection(fetch = FetchType.EAGER)
    val answers: List<Int?>,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
){
    fun toGetQuizResponse() = GetQuizResponse(
        id = id,
        title = question.title,
        text = question.text,
        options = question.options,
    )
}