package app.infra.persistence.adapter

import app.domain.model.Quiz
import app.domain.model.Solution
import app.domain.port.QuizPort
import app.infra.persistence.repository.QuizRepository
import app.infra.persistence.repository.SolutionRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull

class QuizPersistenceAdapter(
    private val quizRepository: QuizRepository,
    private val solutionRepository: SolutionRepository,
): QuizPort {
    override fun getQuiz(id: Int): Quiz? {

        return quizRepository.findByIdOrNull(id)
    }

    override fun addQuiz(quiz: Quiz): Quiz {

        return quizRepository.save(quiz)
    }

    override fun getAllQuizzes(pageable: Pageable): List<Quiz?> {

        return quizRepository.search(pageable = pageable).toList()
    }

    override fun getSolvedQuizzes(userId: Int, pageable: Pageable): List<Solution>? {
        return solutionRepository.findSolutions(id = userId, pageable = pageable)
    }

    override fun countQuizzes(): Long {

        return quizRepository.count()
    }

    override fun removeQuiz(id: Int) {
        return quizRepository.deleteById(id)
    }

    override fun saveSolution(solution: Solution): Solution {
        return solutionRepository.save(solution)
    }

    override fun removeSolutions(quizId: Int) {
        solutionRepository.deleteByQuizId(quizId = quizId)
    }
}

