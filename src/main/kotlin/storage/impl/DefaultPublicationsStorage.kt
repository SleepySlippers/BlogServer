package org.example.storage.impl

import kotlinx.datetime.Clock
import org.example.model.Publication
import org.example.repository.IPublicationsStorage
import kotlin.random.Random

const val LengthThreshold = 500

class DefaultPublicationsStorage : IPublicationsStorage {

    private val publicatoins: MutableSet<Publication> = mutableSetOf()
    override fun getAll(): Collection<Publication> {
        return publicatoins.toList()
    }

    override fun getById(id: Long): Publication? {
        return publicatoins.find { it.id == id }
    }

    override fun createPublication(text: String): Publication? {
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
        publicatoins.add(createdPublication)
        return createdPublication
    }

    override fun deleteById(id: Long): Publication? {
        val popped = getById(id) ?: return null
        publicatoins.remove(popped)
        return popped
    }

    override fun editPublication(id: Long, text: String): Publication? {
        if (text.length > LengthThreshold) {
            return null
        }
        val previous = deleteById(id) ?: return null
        val currentTime = Clock.System.now()
        val createdPublication = Publication(
            id = previous.id,
            text = text,
            creationTime = previous.creationTime,
            editTime = currentTime,
        )
        publicatoins.add(createdPublication)
        return createdPublication
    }
}