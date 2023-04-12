package com.sakamoto.projects.dto

import com.sakamoto.projects.model.Difficulty
import com.sakamoto.projects.model.Project
import com.sakamoto.projects.model.Task

data class ProjectResponse(
    val id: String?,
    val name: String,
    val description: String,
    val tasks: List<Task>,
    val difficulty: Difficulty,
    val estimatedTime: Int
) {
    constructor(project: Project) : this(
        project.id,
        project.name,
        project.description,
        project.tasks,
        project.difficulty,
        project.estimatedTime
    )
}
