package com.domain.resources.schemas

import com.domain.entities.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 200)


    fun mapToUser(result: ResultRow) = User(
        id = result[id],
        name = result[name]
    )
}