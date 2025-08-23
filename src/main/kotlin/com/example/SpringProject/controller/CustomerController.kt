package com.example.SpringProject.controller

import com.example.SpringProject.controller.ApiDocs.CustomerApiDocs
import com.example.SpringProject.model.Customer
import com.example.SpringProject.service.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val service: CustomerService) : CustomerApiDocs {

    override suspend fun getAll(): List<Customer> = service.getAll()

    override suspend fun getById(id: Int): Customer? = service.getById(id)

    override suspend fun create(customer: Customer): Customer = service.create(customer)

    override suspend fun update(id: Int, customer: Customer): Customer? =
        service.update(id, customer)

    override suspend fun delete(id: Int): Boolean = service.delete(id)
}
