package de.squiray.dailytodo.data.db

import android.arch.persistence.room.*
import de.squiray.dailytodo.data.entity.TodoEntity

@Dao
interface TodoDao {

    @get:Query("SELECT * FROM todos")
    val all: List<TodoEntity>

    @Query("SELECT * FROM todos WHERE todoId LIKE :todoId LIMIT 1")
    fun get(todoId: String): TodoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodos(vararg todos: TodoEntity): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTodos(vararg todos: TodoEntity)

    @Delete
    fun deleteTodos(vararg todos: TodoEntity)

}
