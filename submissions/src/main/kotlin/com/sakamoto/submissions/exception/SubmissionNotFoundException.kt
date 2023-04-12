package com.sakamoto.submissions.exception

data class SubmissionNotFoundException(val id: String) : RuntimeException("Submission with id: {$id} not found")
