package com.sakamoto.submissions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sakamoto.submissions"])
class SubmissionsApplication

fun main(args: Array<String>) {
    runApplication<SubmissionsApplication>(*args)
}
