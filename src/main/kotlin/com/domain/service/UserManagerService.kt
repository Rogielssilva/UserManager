package com.domain.service

import com.domain.entities.User
import com.domain.repository.UserManagerRepository

class UserManagerService(private val repository: UserManagerRepository) {

    fun listUsers(): List<User> {
        return repository.listUsers()
    }

    fun createUser(user: User): User {
        return repository.upsertUser(user = user)

    }

    fun deleteUser(id: Int) {
        return repository.delete(id)
    }

    fun getUserById(id: Int): User {
        return repository.getUserById(id)
    }

    fun updateUser(id: Int, user: User): User {
        return repository.upsertUser(id, user)
    }
}