package com.sakamoto.projects.handler

import com.sakamoto.projects.dto.CreateProjectCommand
import com.sakamoto.projects.dto.ErrorResponse
import com.sakamoto.projects.dto.UpdateProjectCommand
import com.sakamoto.projects.dto.ProjectResponse
import com.sakamoto.projects.model.Project
import com.sakamoto.projects.repository.ProjectRepository
import kotlinx.coroutines.flow.transform
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class ProjectsHandler(
    private val repository: ProjectRepository
) {

    suspend fun findAll(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().json().bodyAndAwait(repository.findAll().transform { emit(ProjectResponse(it)) })
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        val project = Project(request.awaitBody<CreateProjectCommand>())
        return repository.existsByName(project.name).let {
            if (it == true) {
                ServerResponse.badRequest().bodyValueAndAwait(ErrorResponse("Project with name ${project.name} already exists", HttpStatus.BAD_REQUEST))
            } else {
                ServerResponse.ok().json().bodyValueAndAwait(ProjectResponse(repository.save(project)))
            }
        }
    }

    suspend fun update(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val project = Project(request.awaitBody<UpdateProjectCommand>())
        project.id = id
        return repository.findById(id)?.let {
            ServerResponse.ok().json().bodyValueAndAwait(ProjectResponse(repository.save(project)))
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun findById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        return repository.findById(id)?.let {
            ServerResponse.ok().json().bodyValueAndAwait(ProjectResponse(it))
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun deleteById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        return repository.findById(id)?.let {
            repository.deleteById(id)
            ServerResponse.ok().buildAndAwait()
        } ?: ServerResponse.notFound().buildAndAwait()
    }
}