package com.domain.repository

import com.domain.resources.schemas.Users
import com.model.User
import io.ktor.features.*
import org.jetbrains.exposed.sql.*


import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.qualifier.qualifier
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
                .first()

            //
        }.also {
            LOG.debug("got info about the userId $id")
        }

    }

    override fun upsertUser(entity: User): User {
        if (entity.id == 0) {
            return createUser(entity)
        }

        try {
            return transaction {
                Users.update({ Users.id eq entity.id }) { row ->
                    row[Users.name] = entity.name
                }
            }.also {
                LOG.debug("userId ${entity.id} was updated")
            }.let {
                return getUserById(it)
            }
        }catch ( e: NoSuchElementException){
            LOG.error("userId ${entity.id} was not found in the updates process")
            throw NotFoundException("userId ${entity.id} was not found")
        }

    }


    override fun delete(id: Int): Int {
        return transaction {
            Users.deleteWhere { Users.id eq id }
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