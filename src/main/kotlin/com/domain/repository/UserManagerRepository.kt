package com.domain.repository

import com.domain.entities.User

interface UserManagerRepository {
    fun listUsers(): List<User>
    fun getUserById(id: Int): User
    fun upsertUser(id: Int = 0, user: User): User
    fun delete(id: Int)
}