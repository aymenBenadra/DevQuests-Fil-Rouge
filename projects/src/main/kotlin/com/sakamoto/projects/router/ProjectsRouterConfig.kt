package com.sakamoto.projects.router

import com.sakamoto.projects.handler.ProjectsHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ProjectsRouterConfig {
    @Bean
    fun router(handler: ProjectsHandler) = coRouter {
        DELETE("{id}", handler::deleteById)
        accept(MediaType.APPLICATION_JSON).nest {
            GET("", handler::findAll)
            GET("{id}", handler::findById)
            contentType(MediaType.APPLICATION_JSON).nest {
                POST("", handler::save)
                PUT("{id}", handler::update)
            }
        }
    }
}
