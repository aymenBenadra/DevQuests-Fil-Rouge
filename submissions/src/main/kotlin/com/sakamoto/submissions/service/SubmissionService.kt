package com.sakamoto.submissions.service

import com.sakamoto.submissions.dto.CompleteSubmissionCommand
import com.sakamoto.submissions.dto.CreateSubmissionCommand
import com.sakamoto.submissions.exception.SubmissionException
import com.sakamoto.submissions.exception.SubmissionNotFoundException
import com.sakamoto.submissions.model.Status
import com.sakamoto.submissions.model.Submission
import com.sakamoto.submissions.repository.SubmissionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import org.springframework.stereotype.Service

@Service
class SubmissionService(private val repository: SubmissionRepository) {

    suspend fun createSubmission(submission: CreateSubmissionCommand): Submission {
        if (repository.existsByUserIdAndProjectId(submission.userId, submission.projectId) == true) {
            throw SubmissionException("Submission already exists")
        }
        return repository.save(Submission(submission))
    }

    suspend fun completeSubmission(submission: CompleteSubmissionCommand): Submission {
        repository.findById(submission.id)?.let {
            if (it.status == Status.COMPLETED) {
                throw SubmissionException("Submission already completed")
            } else if (it.status == Status.CANCELED) {
                throw SubmissionException("Submission already canceled")
            } else if (repository.existsByGithubLink(submission.githubLink) == true) {
                throw SubmissionException("Github link already used")
            } else if (submission.githubLink.isBlank()) {
                throw SubmissionException("Github link cannot be blank")
            }
            return repository.save(it.complete(submission.githubLink))
        }
        throw SubmissionNotFoundException(submission.id)
    }

    suspend fun cancelSubmission(id: String): Submission {
        repository.findById(id)?.let {
            if (it.status == Status.COMPLETED) {
                throw SubmissionException("Submission already completed")
            }
            if (it.status == Status.CANCELED) {
                throw SubmissionException("Submission already canceled")
            }
            return repository.save(it.cancel())
        }
        throw SubmissionNotFoundException(id)
    }

    suspend fun findSubmissionById(id: String): Submission = repository.findById(id)?.let {
        if (it.status == Status.CANCELED) {
            throw SubmissionException("Submission already canceled")
        }
        it
    } ?: throw SubmissionNotFoundException(id)

    fun findAllSubmissionsByUserId(userId: String): Flow<Submission> =
        repository.findAllByUserId(userId).filter { it.status != Status.CANCELED }

    fun findAllSubmissionsByProjectId(projectId: String): Flow<Submission> =
        repository.findAllByProjectId(projectId).filter { it.status != Status.CANCELED }
}