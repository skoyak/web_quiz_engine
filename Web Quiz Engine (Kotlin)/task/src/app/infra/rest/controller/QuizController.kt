package app.infra.rest.controller

import app.domain.handler.QuizHandler
import app.domain.handler.UserHandler
import app.domain.model.Question
import app.domain.model.Quiz
import app.domain.model.toQuizResponse
import app.infra.rest.dto.*
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class QuizController(
    private val quizHandler: QuizHandler,
    private val userHandler: UserHandler,
) {

    @PostMapping("/quizzes")
    fun createQuiz(
        @RequestBody @Valid request: CreateQuizRequest,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<CreateQuizResponse> {
        val user = userHandler.getUser(email = userDetails.username)

        val quiz = Quiz(
            question = Question(title = request.title, text = request.text, options = request.options),
            answers = request.answer,
            user = user,
        )

        quizHandler.addQuiz(quiz = quiz)

        return ResponseEntity(
            CreateQuizResponse(
                id = quiz.id,
                title = quiz.question.title,
                text = quiz.question.text,
                options = quiz.question.options,
            ), HttpStatus.OK
        )
    }

    @PostMapping("/quizzes/{id}/solve")
    fun answerQuiz(
        @PathVariable id: Int,
        @RequestBody request: AnswerQuizRequest,
        @AuthenticationPrincipal userDetails: UserDetails,
    ): ResponseEntity<AnswerQuizResponse> {
        val user = userHandler.getUser(email = userDetails.username)
        println("${user.id} is solving quiz id: $id")

        val result = quizHandler.checkAnswer(user = user, quizId = id, answers = request.answer)

        return ResponseEntity(result.toQuizResponse(), HttpStatus.OK)
    }

    @GetMapping("/quizzes/{id}")
    fun getQuiz(@PathVariable id: Int): ResponseEntity<GetQuizResponse> {

        return ResponseEntity(quizHandler.getQuiz(id = id).toGetQuizResponse(), HttpStatus.OK)
    }

    @GetMapping("/quizzes/completed")
    fun getCompletedQuizzes(
        @RequestParam page: Int,
        @AuthenticationPrincipal userDetails: UserDetails,
    ): ResponseEntity<PaginatedResponse<GetSolvedQuizResponse>> {
        val user = userHandler.getUser(email = userDetails.username)
        println("Getting solved quizzes for user: ${user.id}")

        val itemsPerPage = 10

        val direction = Sort.by(Sort.Direction.DESC, "answeredAt")

        val pageable = PageRequest.of(page, itemsPerPage, direction)

        val solutions = quizHandler.getSolvedQuizzesInPage(userId = user.id!!, pageable = pageable)

        val count = quizHandler.countAllQuizzes().toInt()

        val totalPages = count / itemsPerPage + 1

        val response = PaginatedResponse(
            totalPages = totalPages,
            totalElements = count,
            last = page == totalPages,
            first = page == 0,
            sort = direction,
            number = page,
            numberOfElements = itemsPerPage,
            size = count,
            empty = solutions.isNullOrEmpty(),
            pageable = pageable,
            content = solutions?.map { GetSolvedQuizResponse(id = it.quiz.id!!, completedAt = it.answeredAt)} ?: emptyList()
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/quizzes")
    fun getAllQuizzes(@RequestParam page: Int): ResponseEntity<PaginatedResponse<GetQuizResponse>> {

        val itemsPerPage = 10

        val direction = Sort.unsorted()

        val pageable = PageRequest.of(page, itemsPerPage, direction)

        val quizzes = quizHandler.getAllQuizzesInPage(pageable = pageable)

        val count = quizHandler.countAllQuizzes().toInt()

        val totalPages = count / itemsPerPage + 1

        val response = PaginatedResponse(
            totalPages = totalPages,
            totalElements = count,
            last = page == totalPages,
            first = page == 0,
            sort = direction,
            number = page,
            numberOfElements = itemsPerPage,
            size = count,
            empty = quizzes.isEmpty(),
            pageable = pageable,
            content = quizzes.map { it?.toGetQuizResponse() },
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/quizzes/{id}")
    fun deleteQuiz(
        @PathVariable id: Int,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<HttpStatus> {
        val user = userHandler.getUser(email = userDetails.username)

        quizHandler.removeQuiz(quizId = id, userId = user.id!!)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}