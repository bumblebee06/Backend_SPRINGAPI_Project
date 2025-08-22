package com.example.crudwebfluxkotlin.repository

import com.example.crudwebfluxkotlin.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository : ReactiveMongoRepository<User, String>
