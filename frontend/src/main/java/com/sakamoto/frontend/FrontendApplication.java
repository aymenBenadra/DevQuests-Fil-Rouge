package com.sakamoto.frontend;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sakamoto.frontend")
public class FrontendApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

}
