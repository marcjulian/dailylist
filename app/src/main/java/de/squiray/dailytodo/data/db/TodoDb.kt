package de.squiray.dailytodo.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import de.squiray.dailytodo.data.entity.TodoEntity

@Database(version = 1,
        entities = arrayOf(TodoEntity::class))
@TypeConverters(TodoConverter::class)
abstract class TodoDb : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        @Volatile private var INSTANCE: TodoDb? = null

        fun getInstance(context: Context): TodoDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        TodoDb::class.java, "todo.db")
                        .build()
    }

}
