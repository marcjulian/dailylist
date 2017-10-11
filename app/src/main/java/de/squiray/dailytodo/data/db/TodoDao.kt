package de.squiray.dailytodo.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import de.squiray.dailytodo.domain.entity.Todo

@Dao
interface TodoDao {

    @get:Query("SELECT * FROM todos")
    val all: List<Todo>

    @Query("SELECT * FROM todos WHERE todoId LIKE :todoId LIMIT 1")
    fun get(todoId: String): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodos(vararg todos: Todo): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTodos(vararg todos: Todo)

    @Delete
    fun deleteTodos(vararg todos: Todo)

}
