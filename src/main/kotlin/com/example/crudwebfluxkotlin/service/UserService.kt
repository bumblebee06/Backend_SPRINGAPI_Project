package com.example.crudwebfluxkotlin.service

import com.example.crudwebfluxkotlin.model.User
import com.example.crudwebfluxkotlin.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(private val repository: UserRepository) {

    fun getAllUsers(): Flux<User> = repository.findAll()

    fun getUserById(id: String): Mono<User> = repository.findById(id)

    fun createUser(user: User): Mono<User> = repository.save(user)

    fun updateUser(id: String, user: User): Mono<User> =
        repository.findById(id)
            .flatMap {
                it.name = user.name
                it.email = user.email
                repository.save(it)
            }

    fun deleteUser(id: String): Mono<Void> = repository.deleteById(id)
}
