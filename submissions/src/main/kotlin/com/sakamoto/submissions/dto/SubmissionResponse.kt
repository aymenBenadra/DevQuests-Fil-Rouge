package com.sakamoto.submissions.dto

import com.sakamoto.submissions.model.Status
import com.sakamoto.submissions.model.Submission

data class SubmissionResponse(
    val id: String,
    val githubLink: String,
    val status: Status,
    val userId: String,
    val projectId: String,
    val createdAt: String,
) {
    constructor(submission: Submission) : this(
        submission.id!!,
        submission.githubLink ?: "",
        submission.status,
        submission.userId,
        submission.projectId,
        submission.createdAt.toString(),
    )
}
