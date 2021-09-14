package com.domain.service

import com.infrastrutucture.repositoriesImpl.UserManagerRepositoryImpl
import com.domain.entities.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

private const val USER_ID = 1

class UserManagerServiceTest {
    private var repository = mockk<UserManagerRepositoryImpl>()
    private var service: UserManagerService = UserManagerService(repository)

    @Before
    fun setUp() {
        service = UserManagerService(repository)
    }

    @Test
    fun testDeleteUserByID() {
        every { repository.delete(USER_ID) } returns 1
        val result = service?.deleteUser(USER_ID)

        verify { repository.delete(USER_ID) }
        assertNotNull(result)
        assertEquals(USER_ID, result)
    }

    @Test
    fun testGetUserByID() {
        every { repository.getUserById(USER_ID) } returns buildUser()

        val user = service?.getUserById(USER_ID)

        assertNotNull(user)
        assertEquals(buildUser(), user)
    }

    @Test
    fun testListUsers() {
        every { repository.listUsers() } returns listOf<User>(buildUser())

        val users = service?.listUsers()

        assertNotNull(users)
        Assertions.assertTrue(users.isNotEmpty())
    }

    @Test
    fun testListUsersEmpty() {
        every { repository.listUsers() } returns listOf()

        val users = service?.listUsers()

        assertNotNull(users)
        Assertions.assertFalse(users.isNotEmpty())
    }


    @Test
    fun testCreateUser() {
        val entity = User("test")
        every { repository.upsertUser(entity) } returns buildUser()

        val user = service?.createUser(entity)

        assertNotNull(user)

        assertEquals(entity.name, user.name)
    }

    private fun buildUser(): User {
        return User("test", USER_ID)
    }
}