package com.sakamoto.submissions.config

import com.sakamoto.submissions.dto.ErrorResponse
import com.sakamoto.submissions.exception.SubmissionException
import com.sakamoto.submissions.exception.SubmissionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(SubmissionNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleSubmissionNotFoundException(e: SubmissionNotFoundException) =
        ErrorResponse(e.message ?: "Submission not found", HttpStatus.NOT_FOUND)

    @ExceptionHandler(SubmissionException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleSubmissionException(e: SubmissionException) = ErrorResponse(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception) = ErrorResponse(e.message ?: "That's weird...", HttpStatus.INTERNAL_SERVER_ERROR)
}