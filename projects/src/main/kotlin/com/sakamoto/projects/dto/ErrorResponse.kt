package com.sakamoto.projects.dto

import org.springframework.http.HttpStatus
import java.time.Instant

data class ErrorResponse(val message: String, val status: HttpStatus, val date: Instant = Instant.now())
