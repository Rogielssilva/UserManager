package com.repository

import com.model.User

class UserManagerRepositoryImpl : UserManagerRepository {
    override fun createUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun listUsers(): List<User> {
        return arrayListOf(User("name"), User("name2"))
    }

    override fun getUserById(): User {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}