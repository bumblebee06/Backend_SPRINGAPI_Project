package com.example.demo.controller

import com.example.demo.model.User
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): Flux<User> = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): Mono<User> = userService.getUserById(id)

    @PostMapping
    fun createUser(@RequestBody user: User): Mono<User> = userService.createUser(user)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): Mono<User> =
        userService.updateUser(id, user)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): Mono<Void> = userService.deleteUser(id)
}
