package com.sakamoto.submissions.exception

data class SubmissionException(override val message: String) : Exception(message)
