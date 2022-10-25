package com.example.springbootkotlinpractice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// JUnit 5 allows to inject constructor and method parameters, which is a good fit with Kotlin read-only and non-nullable properties
class IntegrationTest(@Autowired val restTemplate: TestRestTemplate) {


    // junit.jupiter.testinstance.lifecycle.default = per_class
    // JUnit 5 requires this function to be static, because test classes are instantiated one time per test
    // allows you to change this default behavior and instantiate test classes one time per class by using junit-platform.properties
    @BeforeAll
    fun setUp() {
        println(">>> setup")
    }

    @AfterAll
    fun tearDown() {
        println(">> Tear down")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>")
    }

}
