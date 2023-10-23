package app.domain.exception

class UnauthorizedQuizOperation: BusinessException(message = "You can't delete others quizzes!")