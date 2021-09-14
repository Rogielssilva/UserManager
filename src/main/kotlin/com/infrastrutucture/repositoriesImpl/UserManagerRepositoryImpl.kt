package com.infrastrutucture.repositoriesImpl

import com.domain.repository.UserManagerRepository
import com.domain.resources.schemas.Users
import com.domain.entities.User
import io.ktor.features.*
import org.jetbrains.exposed.sql.*


import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

class UserManagerRepositoryImpl : UserManagerRepository {
    private val LOG = LoggerFactory.getLogger(UserManagerRepository::class.java)

    override fun listUsers(): List<User> {
        return transaction {
            Users.selectAll().map { resultRow ->
                Users.mapToUser(resultRow)
            }
        }
    }

    override fun getUserById(id: Int): User {
        return transaction {
            Users.select { Users.id eq id }
                .map { Users.mapToUser(it) }
                .firstOrNull() ?: throw NotFoundException("userId :${id} not found")
        }.also {
            LOG.debug("got info about the userId $id")
        }
    }

    override fun upsertUser(userId: Int, entity: User): User {
        if (userId == 0) {
            return createUser(entity)
        }

        try {
            return transaction {
                Users.update({ Users.id eq userId }) { row ->
                    row[this.name] = entity.name
                }
            }.let {
                return getUserById(userId)
            }.also {
                LOG.debug("userId ${entity.id} was updated")
            }
        } catch (e: NoSuchElementException) {
            LOG.error("userId ${entity.id} was not found in the updates process")
            throw NotFoundException("userId ${entity.id} was not found")
        }
    }

    override fun delete(id: Int) {
        return transaction {
            Users.deleteWhere { Users.id eq id }
                .takeIf { it == 1 } ?: throw NotFoundException("userid $id was not found")
        }

    }


    private fun createUser(entity: User): User {
        return transaction {
            val result = Users.insert { row ->
                row[name] = entity.name
            }

            entity.copy(id = result.get(Users.id))
        }.also {
            LOG.debug("user ${entity.name} was created")
        }
    }
}