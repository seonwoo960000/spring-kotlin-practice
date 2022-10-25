package com.example.springbootkotlinpractice.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("application")
data class ApplicationProperties(var title: String, val banner: Banner) {
    // val -> must be non-null
    data class Banner(val title: String? = null, val content: String)
}