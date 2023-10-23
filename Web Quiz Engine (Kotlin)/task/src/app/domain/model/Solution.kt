package app.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.Instant

@Entity
data class Solution(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @ManyToOne
    @JoinColumn(name ="quiz_id")
    val quiz: Quiz,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    val answeredAt: Instant,
)
