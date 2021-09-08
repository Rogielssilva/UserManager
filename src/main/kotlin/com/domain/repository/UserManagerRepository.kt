package com.domain.repository

import com.model.User

interface UserManagerRepository {
    fun listUsers(): List<User>
    fun getUserById(id: Int): User
    fun upsertUser(user: User): User
    fun delete(id: Int): Int
}