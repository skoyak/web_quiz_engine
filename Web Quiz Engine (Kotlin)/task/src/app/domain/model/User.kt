package app.domain.model

import jakarta.persistence.*

@Entity(name = "app_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    val email: String,

    val password: String,

    @ElementCollection(fetch = FetchType.EAGER)
    val authorities: List<String>,

    @OneToMany(mappedBy = "user")
    var quizzes: List<Quiz>? = emptyList(),

    @OneToMany(mappedBy = "user")
    var solutions: List<Solution>? = emptyList()
)