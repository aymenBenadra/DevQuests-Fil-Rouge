package com.sakamoto.projects.dto

import com.sakamoto.projects.model.Difficulty
import com.sakamoto.projects.model.Task

data class UpdateProjectCommand(
    val id: String,
    val name: String,
    val description: String,
    val tasks: List<Task>,
    val difficulty: Difficulty
)
