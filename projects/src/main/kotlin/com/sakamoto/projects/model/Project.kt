package com.sakamoto.projects.model

import com.sakamoto.projects.dto.CreateProjectCommand
import com.sakamoto.projects.dto.UpdateProjectCommand
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "projects")
data class Project(
    @Id var id: String?, val name: String, val description: String, val tasks: List<Task>, val difficulty: Difficulty
) {
    val estimatedTime: Int get() = tasks.sumOf { it.estimatedTime }

    constructor(project: CreateProjectCommand) : this(
        null, project.name, project.description, project.tasks, project.difficulty
    )

    constructor(project: UpdateProjectCommand) : this(
        project.id, project.name, project.description, project.tasks, project.difficulty
    )
}
