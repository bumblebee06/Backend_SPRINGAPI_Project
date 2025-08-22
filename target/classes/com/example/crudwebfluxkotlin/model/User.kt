package com.example.crudwebfluxkotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Id val id: String? = null,
    var name: String,
    var email: String
)
