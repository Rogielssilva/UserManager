package com.domain.repository

import com.model.User

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
        return this.users.find { user -> user.id == id }!!
    }

    override fun upsertUser(userToUpdate: User): User {
        for (user in users) {
            if (user.id == userToUpdate.id) {
                users.remove(user)
                users.add(userToUpdate)
            }

    }
        //Handle with the problem when the id doesn't exist
        return userToUpdate

}

override fun delete(id: Int) {
    // DB mock
    for (user in this.users) {
        if (user?.id == id) {
            this.users.remove(user)
            break
        }
    }
}
}