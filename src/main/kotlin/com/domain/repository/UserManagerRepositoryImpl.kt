package com.domain.repository

import com.model.User
import io.ktor.features.*

class UserManagerRepositoryImpl : UserManagerRepository {
    private val users = mutableListOf<User>()
    private var idCount = 1

    override fun createUser(user: User): User {
        //DB mock
        user.id = idCount++
        this.users.add(user)
        return user
    }

    override fun listUsers(): List<User> {
        return this.users
    }

    override fun getUserById(id: Int): User {
        val user = this.users.find { user -> user.id == id }
        checkIfExists(id, user)
        return user!!
    }

    override fun upsertUser(userToUpdate: User): User {
        getUserById(userToUpdate.id).apply {
            users.remove(this)
            users.add(userToUpdate)
        }
        return userToUpdate
    }

    override fun delete(id: Int) {
        getUserById(id).apply {
            users.remove(this)
        }
    }

    private fun checkIfExists(id: Int, user: User?) {
        if (user === null) {
            throw NotFoundException("user id $id not found")
        }
    }
}