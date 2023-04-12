package com.sakamoto.submissions.model

import com.sakamoto.submissions.dto.CreateSubmissionCommand
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "submissions")
data class Submission(@Id val id: String?, val githubLink: String?, val status: Status, val userId: String, val projectId: String, val createdAt: Instant?) {

    constructor(submission: CreateSubmissionCommand) : this(null, null, Status.WIP, submission.userId, submission.projectId, Instant.now())

    fun complete(githubLink: String) = copy(status = Status.COMPLETED, githubLink = githubLink)

    fun cancel() = copy(status = Status.CANCELED)
}
