package com.example.springbootkotlinpractice.domain.user

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}