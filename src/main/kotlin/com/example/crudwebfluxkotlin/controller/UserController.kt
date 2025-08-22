package com.example.crudwebfluxkotlin.controller

import com.example.crudwebfluxkotlin.model.User
import com.example.crudwebfluxkotlin.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController(private val service: UserService) {

    @GetMapping
    fun getAll(): Flux<User> = service.getAllUsers()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): Mono<User> = service.getUserById(id)

    @PostMapping
    fun create(@Valid @RequestBody user: User): Mono<User> = service.createUser(user)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @Valid @RequestBody user: User): Mono<User> =
        service.updateUser(id, user)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Mono<Void> = service.deleteUser(id)
}
