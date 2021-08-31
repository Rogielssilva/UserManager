package com.repository

import com.model.User

interface UserManagerRepository {
    fun createUser(user: User)
    fun listUsers(): List<User>
    fun getUserById(): User
    fun delete(id: Int): Unit
}