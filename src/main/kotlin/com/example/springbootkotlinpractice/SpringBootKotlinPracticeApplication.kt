package com.example.springbootkotlinpractice

import com.example.springbootkotlinpractice.infrastructure.ApplicationProperties
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties::class)
class SpringBootKotlinPracticeApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinPracticeApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}

