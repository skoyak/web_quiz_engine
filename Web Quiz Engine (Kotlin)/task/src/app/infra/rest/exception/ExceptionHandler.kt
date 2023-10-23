package app.infra.rest.exception

import app.domain.exception.BusinessException
import app.domain.exception.QuizNotFoundException
import app.domain.exception.UnauthorizedQuizOperation
import app.domain.exception.UserAlreadyExistsException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        ex: BusinessException,
    ): ResponseEntity<Any> {
        return when (ex) {
            is QuizNotFoundException -> ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
            is UnauthorizedQuizOperation -> ResponseEntity(ex.message, HttpStatus.FORBIDDEN)
            is UserAlreadyExistsException -> ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
            else -> ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleArgumentNotValidException(
        ex: ConstraintViolationException,
    ): ProblemDetail {
        println("message: ${ex.message}")
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.message!!)
            .apply { this.title = "Validation error happened" }
    }
}