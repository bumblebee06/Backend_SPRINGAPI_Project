package com.example.SpringProject.service

import com.example.SpringProject.model.Customer   // ✅ Add this line
import com.example.SpringProject.repository.CustomerRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class CustomerService(private val repository: CustomerRepository) {

    suspend fun getAll(): List<Customer> = repository.findAll().toList()

    suspend fun getById(id: Int): Customer? = repository.findById(id)

    suspend fun create(customer: Customer): Customer = repository.save(customer)

    suspend fun update(id: Int, updated: Customer): Customer? {
        val existing = repository.findById(id) ?: return null
        val newCustomer = existing.copy(name = updated.name, email = updated.email)
        return repository.save(newCustomer)
    }

    suspend fun delete(id: Int): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else false
    }
}
