package com.sakamoto.projects

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sakamoto.projects"])
class ProjectsApplication

fun main(args: Array<String>) {
    runApplication<ProjectsApplication>(*args)
}