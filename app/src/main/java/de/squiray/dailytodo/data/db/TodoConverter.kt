package de.squiray.dailytodo.data.db

import android.arch.persistence.room.TypeConverter

import de.squiray.dailytodo.domain.entity.TodoType

class TodoConverter {

    @TypeConverter
    fun fromString(value: String?): TodoType? {
        return if (value == null) null else TodoType.valueOf(value)
    }

    @TypeConverter
    fun todoTypeToString(todoType: TodoType?): String? {
        return todoType?.name
    }
}
