package com.service

import com.model.User
import com.repository.UserManagerRepository

class UserManagerService(val repository: UserManagerRepository) {
    fun listUsers(): List<User> {
        return repository.listUsers()
    }
}