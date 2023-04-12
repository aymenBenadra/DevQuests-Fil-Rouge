package com.sakamoto.submissions.repository

import com.sakamoto.submissions.model.Submission
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface SubmissionRepository : CoroutineCrudRepository<Submission, String> {
    suspend fun existsByGithubLink(githubLink: String): Boolean?

    suspend fun existsByUserIdAndProjectId(userId: String, projectId: String): Boolean?

    fun findAllByUserId(userId: String): Flow<Submission>

    fun findAllByProjectId(projectId: String): Flow<Submission>
}