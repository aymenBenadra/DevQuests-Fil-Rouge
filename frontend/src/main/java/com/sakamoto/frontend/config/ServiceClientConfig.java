package com.sakamoto.frontend.config;

import com.sakamoto.frontend.data.service.ProjectService;
import com.sakamoto.frontend.data.service.SubmissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ServiceClientConfig {

    @Bean
    public ProjectService projectService() {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(ProjectService.class);
    }

    @Bean
    public SubmissionService submissionService() {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8082").build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(SubmissionService.class);
    }
}
