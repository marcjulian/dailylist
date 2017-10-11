package de.squiray.dailytodo.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import de.squiray.dailytodo.domain.entity.Todo

@Database(version = 1,
        entities = arrayOf(Todo::class))
@TypeConverters(TodoConverter::class)
abstract class TodoDb : RoomDatabase() {

    abstract fun todoDao(): TodoDao

}
