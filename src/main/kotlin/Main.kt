package org.example

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.example.api.publicationApi
import org.koin.ktor.plugin.Koin

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    embeddedServer(Netty, port = 8080) {
        configureServer()
        publicationApi()
    }.start(wait = true)
}

fun Application.configureServer() {
    install(Koin){
        modules(publicationsModule)
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        )
    }
}