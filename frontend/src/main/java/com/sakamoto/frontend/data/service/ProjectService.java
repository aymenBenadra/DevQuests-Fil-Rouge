package com.sakamoto.frontend.data.service;

import com.sakamoto.frontend.data.dto.CreateProjectCommand;
import com.sakamoto.frontend.data.dto.UpdateProjectCommand;
import com.sakamoto.frontend.data.model.Project;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/api/projects")
public interface ProjectService {

    @GetExchange(accept = "application/json")
    Flux<Project> getProjects();

    @GetExchange(value = "/{id}", accept = "application/json")
    Mono<Project> getProject(@PathVariable String id);

    @PostExchange(accept = "application/json", contentType = "application/json")
    Mono<Project> saveProject(@RequestBody CreateProjectCommand projectCommand);

    @PutExchange(value = "/{id}", accept = "application/json", contentType = "application/json")
    Mono<Project> updateProject(@PathVariable String id, @RequestBody UpdateProjectCommand projectCommand);

    @DeleteExchange("/{id}")
    Mono<Void> deleteProject(@PathVariable String id);
}
