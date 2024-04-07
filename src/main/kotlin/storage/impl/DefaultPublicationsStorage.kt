package org.example.storage.impl

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock
import org.example.model.Publication
import org.example.storage.PublicationsStorage
import kotlin.random.Random

const val LengthThreshold = 500

class DefaultPublicationsStorage : PublicationsStorage {
    private val mutex = Mutex()
    private val publications: MutableSet<Publication> = mutableSetOf()
    override suspend fun getAll(): Collection<Publication> {
        return mutex.withLock { publications.toList() }
    }

    override suspend fun getById(id: Long): Publication? {
        return mutex.withLock {  publications.find { it.id == id } }
    }

    override suspend fun createPublication(text: String): Publication? {
        if (text.length > LengthThreshold) {
            return null
        }
        val currentTime = Clock.System.now()
        val createdPublication = Publication(
            id = Random.nextLong(),
            text = text,
            creationTime = currentTime,
            editTime = currentTime,
        )
        mutex.withLock { publications.add(createdPublication) }
        return createdPublication
    }

    override suspend fun deleteById(id: Long): Publication? {
        val popped = getById(id) ?: return null
        publications.remove(popped)
        return popped
    }

    override suspend fun editPublication(id: Long, text: String): Publication? {
        if (text.length > LengthThreshold) {
            return null
        }
        val currentTime = Clock.System.now()
        val previous = deleteById(id) ?: return null
        val createdPublication = Publication(
            id = previous.id,
            text = text,
            creationTime = previous.creationTime,
            editTime = currentTime,
        )
        mutex.withLock { publications.add(createdPublication) }
        return createdPublication
    }
}