package com.domain.service

import com.model.User
import com.domain.repository.UserManagerRepository
import org.slf4j.LoggerFactory
import kotlin.jvm.Throws

class UserManagerService(private val repository: UserManagerRepository) {
    private val log = LoggerFactory.getLogger(UserManagerService::class.java)

    fun listUsers(): List<User> {
        return repository.listUsers()
    }

    fun createUser(user: User): User {
        return repository.upsertUser(user)

    }

    fun deleteUser(id: Int): Int {
        return repository.delete(id)
    }


    fun getUserById(id: Int): User {
        return repository.getUserById(id)
    }

    fun updateUser(user: User): User {
        return repository.upsertUser(user)
    }
}