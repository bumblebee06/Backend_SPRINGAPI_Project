package com.example.demo.service

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): Flux<User> = userRepository.findAll()

    fun getUserById(id: Long): Mono<User> = userRepository.findById(id)

    fun createUser(user: User): Mono<User> = userRepository.save(user.copy(id = null))

    fun updateUser(id: Long, user: User): Mono<User> =
        userRepository.findById(id).flatMap {
            val updated = it.copy(name = user.name, email = user.email)
            userRepository.save(updated)
        }

    fun deleteUser(id: Long): Mono<Void> = userRepository.deleteById(id)
}
