package com.example.crudwebfluxkotlin

import com.example.crudwebfluxkotlin.model.User
import com.example.crudwebfluxkotlin.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Mono

@SpringBootApplication
class CrudwebfluxkotlinApplication {

    @Bean
    fun testMongo(userRepository: UserRepository) = CommandLineRunner {
        println("Checking MongoDB connection...")

        // If collection is empty, insert a test user
        userRepository.count().flatMap { count ->
            if (count == 0L) {
                userRepository.save(User(name = "Test User", email = "test@example.com"))
            } else {
                Mono.empty()
            }
        }.subscribe(
            { println("Test user inserted or already exists") },
            { error -> println("Error initializing test user: ${error.message}") }
        )

        // Print all users
        userRepository.findAll().subscribe { println("Found user: $it") }
    }
}

fun main(args: Array<String>) {
    runApplication<CrudwebfluxkotlinApplication>(*args)
}
