package com.sakamoto.projects.repository

import com.sakamoto.projects.model.Project
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ProjectRepository : CoroutineCrudRepository<Project, String> {
    suspend fun existsByName(name: String): Boolean?
}