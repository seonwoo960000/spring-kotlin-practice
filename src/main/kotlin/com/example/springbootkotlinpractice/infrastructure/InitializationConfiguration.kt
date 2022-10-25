package com.example.springbootkotlinpractice.infrastructure

import com.example.springbootkotlinpractice.domain.article.Article
import com.example.springbootkotlinpractice.domain.article.ArticleRepository
import com.example.springbootkotlinpractice.domain.user.User
import com.example.springbootkotlinpractice.domain.user.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InitializationConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner {
        val author = userRepository.save(User("smaldini", "Stéphane", "Maldini"))
        articleRepository.save(
            Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = author
            )
        )
        articleRepository.save(
            Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = author
            )
        )
    }

    @Bean
    fun applicationProperties(applicationProperties: ApplicationProperties) = ApplicationRunner {
        println("application properties: ${applicationProperties}")
    }
}