package de.squiray.dailytodo.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import de.squiray.dailytodo.domain.entity.TodoType
import java.util.*

@Entity(tableName = "todos")
data class TodoEntity(@PrimaryKey
                      @ColumnInfo(name = "todoId")
                      val id: String = UUID.randomUUID().toString(),
                      @ColumnInfo(name = "todo")
                      var todo: String,
                      @ColumnInfo(name = "todoType")
                      var todoType: TodoType,
                      @ColumnInfo(name = "completed")
                      var completed: Boolean)
