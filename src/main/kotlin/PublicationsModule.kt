package org.example

import org.example.storage.PublicationsStorage
import org.example.storage.impl.DefaultPublicationsStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val publicationsModule = module {
    singleOf(::DefaultPublicationsStorage) bind PublicationsStorage::class
}