package de.squiray.dailylist.data.entity.mapper

import java.util.*

abstract class EntityMapper<E, D> internal constructor() {

    fun fromEntities(entities: Iterable<E>): List<D> {
        val result = ArrayList<D>()
        entities.forEach { entity -> result.add(fromEntity(entity)) }
        return result
    }

    fun toEntities(domainObjects: Iterable<D>): List<E> {
        val result = ArrayList<E>()
        domainObjects.forEach { domain -> result.add(toEntity(domain)) }
        return result
    }

    abstract fun fromEntity(entity: E): D

    abstract fun toEntity(domainObject: D): E

}
