package com.domain.resources.schemas

import com.model.User
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.Table


object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 200)


    fun mapToUser(result: ResultRow) = User(
        id = result[id],
        name = result[name]
    )
}