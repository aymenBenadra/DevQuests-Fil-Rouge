package com.sakamoto.submissions.function

import com.sakamoto.submissions.dto.CompleteSubmissionCommand
import com.sakamoto.submissions.dto.CreateSubmissionCommand
import com.sakamoto.submissions.dto.SubmissionResponse
import com.sakamoto.submissions.service.SubmissionService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function

@Configuration
class SubmissionFunctions(val submissionService: SubmissionService) {

    @Bean
    fun create(): Function<CreateSubmissionCommand, SubmissionResponse> = Function {
        runBlocking {
            SubmissionResponse(submissionService.createSubmission(it))
        }
    }

    @Bean
    fun complete(): Function<CompleteSubmissionCommand, SubmissionResponse> = Function {
        runBlocking {
            SubmissionResponse(submissionService.completeSubmission(it))
        }
    }

    @Bean
    fun cancel(): Function<String, SubmissionResponse> = Function {
        runBlocking {
            SubmissionResponse(submissionService.cancelSubmission(it))
        }
    }

    @Bean
    fun find(): Function<String, SubmissionResponse> = Function {
        runBlocking {
            SubmissionResponse(submissionService.findSubmissionById(it))
        }
    }

    @Bean
    fun findForProject(): Function<String, Flow<SubmissionResponse>> = Function {
        submissionService.findAllSubmissionsByProjectId(it).map { submission ->
            SubmissionResponse(submission)
        }
    }

    @Bean
    fun findForUser(): Function<String, Flow<SubmissionResponse>> = Function {
        submissionService.findAllSubmissionsByUserId(it).map { submission ->
            SubmissionResponse(submission)
        }
    }
}