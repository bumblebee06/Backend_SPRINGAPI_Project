package com.example.SpringProject.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customers")
data class Customer(
    @Id val id: Int? = null,
    val name: String,
    val email: String
)
