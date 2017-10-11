package de.squiray.dailytodo.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todos")
data class Todo(@PrimaryKey
                @ColumnInfo(name = "todoId")
                val id: String = UUID.randomUUID().toString(),
                @ColumnInfo(name = "todo")
                var todo: String,
                @ColumnInfo(name = "todoType")
                var todoType: TodoType)
