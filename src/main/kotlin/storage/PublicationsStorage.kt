package org.example.storage

import org.example.model.Publication

interface PublicationsStorage {
    suspend fun getAll(): Collection<Publication>

    suspend fun getById(id: Long): Publication?

    suspend fun createPublication(text: String): Publication?

    suspend fun editPublication(id: Long, text: String): Publication?

    suspend fun deleteById(id: Long): Publication?
}