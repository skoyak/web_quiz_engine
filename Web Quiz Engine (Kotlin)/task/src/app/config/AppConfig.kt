package app.config

import app.domain.handler.QuizHandler
import app.domain.handler.UserHandler
import app.domain.port.QuizPort
import app.domain.port.UserPort
import app.infra.persistence.adapter.QuizPersistenceAdapter
import app.infra.persistence.adapter.UserPersistenceAdapter
import app.infra.persistence.repository.QuizRepository
import app.infra.persistence.repository.SolutionRepository
import app.infra.persistence.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AppConfig {

    @Bean
    fun createQuizHandler(quizPort: QuizPort): QuizHandler {
        return QuizHandler(quizPort = quizPort)
    }

    @Bean
    fun createQuizPersistenceAdapter(
        repository: QuizRepository,
        solutionRepository: SolutionRepository,
    ): QuizPersistenceAdapter {
        return QuizPersistenceAdapter(
            quizRepository = repository,
            solutionRepository = solutionRepository,
        )
    }

    @Bean
    fun createUserHandler(userPort: UserPort, encoder: PasswordEncoder): UserHandler {
        return UserHandler(userPort = userPort, encoder = encoder)
    }

    @Bean
    fun createUserPersistenceAdapter(repository: UserRepository): UserPersistenceAdapter {
        return UserPersistenceAdapter(repository = repository)
    }
}