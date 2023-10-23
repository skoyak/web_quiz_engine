package app.domain.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Embeddable
data class Question(
    val title: String,
    val text: String,

    @ElementCollection
    @Fetch(value = FetchMode.SUBSELECT)
    val options: List<String>
)
