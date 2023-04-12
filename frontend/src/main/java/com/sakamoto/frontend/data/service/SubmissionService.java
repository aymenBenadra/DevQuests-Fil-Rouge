package com.sakamoto.frontend.data.service;

import com.sakamoto.frontend.data.dto.CompleteSubmissionCommand;
import com.sakamoto.frontend.data.dto.CreateSubmissionCommand;
import com.sakamoto.frontend.data.model.Submission;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/api/submissions")
public interface SubmissionService {
    @PostExchange(value = "/findForProject", accept = "application/json", contentType = "text/plain")
    Flux<Submission> getProjectSubmissions(String projectId);

    @PostExchange(value = "/findForUser", accept = "application/json", contentType = "text/plain")
    Flux<Submission> getUserSubmissions(String userId);

    @PostExchange(value = "/find", accept = "application/json", contentType = "text/plain")
    Mono<Submission> getSubmission(String id);

    @PostExchange(value = "/create", accept = "application/json", contentType = "application/json")
    Mono<Submission> saveSubmission(@RequestBody CreateSubmissionCommand submissionCommand);

    @PostExchange(value = "/complete", accept = "application/json", contentType = "application/json")
    Mono<Submission> completeSubmission(@RequestBody CompleteSubmissionCommand submissionCommand);

    @PostExchange(value = "/cancel", accept = "application/json", contentType = "text/plain")
    Mono<Submission> cancelSubmission(String id);
}
