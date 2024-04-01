package org.example

import org.example.repository.IPublicationsStorage
import org.example.storage.impl.DefaultPublicationsStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val publicationsModule = module {
    singleOf(::DefaultPublicationsStorage) bind IPublicationsStorage::class
}