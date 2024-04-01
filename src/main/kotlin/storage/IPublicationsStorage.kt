package org.example.repository

import org.example.model.Publication

interface IPublicationsStorage {
    fun getAll(): Collection<Publication>

    fun getById(id: Long): Publication?

    fun createPublication(text: String): Publication?

    fun editPublication(id: Long, text: String): Publication?

    fun deleteById(id: Long): Publication?
}