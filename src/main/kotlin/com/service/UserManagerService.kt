package com.service

import com.model.User
import com.repository.UserManagerRepository

class UserManagerService(private val repository: UserManagerRepository) {
    fun listUsers(): List<User> {
        return repository.listUsers()
    }

    fun createUser(user: User): User {
        return repository.createUser(user)
    }
}