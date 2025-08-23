package com.example.SpringProject.controller.ApiDocs

import com.example.SpringProject.model.Customer
//import com.jayway.jsonpath.internal.path.ArraySliceOperation.Operation.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(
    name = "Customers API",
    description = "Manage customers (v1). Provides CRUD operations."
)
interface CustomerApiDocs {
    annotation class Tag(val name: String, val description: String)

    @Operation(
        summary = "Get all customers",
        description = "Returns a list of all customers in the system.",
        responses = [
            ApiResponse(responseCode = "200", description = "List of customers retrieved successfully"),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    @GetMapping
    suspend fun getAll(): List<Customer>

    @Operation(
        summary = "Get customer by ID",
        responses = [
            ApiResponse(responseCode = "200", description = "Customer found",
                content = [Content(schema = Schema(implementation = Customer::class))]),
            ApiResponse(responseCode = "404", description = "Customer not found", content = [Content()])
        ]
    )
    @GetMapping("/{id}")
    suspend fun getById(
        @Parameter(description = "Customer ID") @PathVariable id: Int
    ): Customer?

    @Operation(
        summary = "Create a new customer",
        responses = [
            ApiResponse(responseCode = "201", description = "Customer created successfully"),
            ApiResponse(responseCode = "400", description = "Invalid input", content = [Content()])
        ]
    )
    @PostMapping
    suspend fun create(@RequestBody customer: Customer): Customer

    @Operation(
        summary = "Update an existing customer",
        responses = [
            ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            ApiResponse(responseCode = "404", description = "Customer not found", content = [Content()])
        ]
    )
    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: Int,
        @RequestBody customer: Customer
    ): Customer?

    @Operation(
        summary = "Delete a customer",
        responses = [
            ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
            ApiResponse(responseCode = "404", description = "Customer not found", content = [Content()])
        ]
    )
    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Int): Boolean
}
