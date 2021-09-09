package com.domain.service


import com.domain.repository.UserManagerRepositoryImpl
import com.model.User
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserManagerServiceTest {

    private var repository = mockk<UserManagerRepositoryImpl>()
    private var service: UserManagerService = UserManagerService(repository)

    @Before
    fun setUp() {
        service = UserManagerService(repository)
    }

    @Test
    fun testDeleteUserByID() {
        every { repository.delete(1) } returns 1
        val result = service?.deleteUser(1)

        assertNotNull(result)
        assertEquals(1, result)
    }

    @Test
    fun testGetUserByID() {
        every { repository.getUserById(1) } returns buildUser()

        val user = service?.getUserById(1)

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
        return User("test", 1)
    }

}