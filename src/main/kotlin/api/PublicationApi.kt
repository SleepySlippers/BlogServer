package org.example.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.api.model.CreatePublicationRequest
import org.example.api.model.EditPublicationRequest
import org.example.repository.IPublicationsStorage
import org.koin.ktor.ext.inject

const val prefix = "/publications"

fun Application.publicationApi() {
    routing {

        val publicationsStorage by inject<IPublicationsStorage>()

        get("${prefix}/all") {
            try {
                val allPublications = publicationsStorage.getAll()
                call.respond(allPublications)
            } catch (ex: Exception) {
                println(ex.message)
            }
        }

        get("${prefix}/{id}/get") {
            try {
                val publicationId = call.parameters["id"]?.toLong() ?: 0

                val publication = publicationsStorage.getById(publicationId)

                if (publication == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(publication)
                }
            } catch (ex: Exception) {
                println(ex.message)
            }
        }

        patch("${prefix}/{id}/edit") {
            try {
                val publicationId = call.parameters["id"]?.toLong() ?: 0

                val request = call.receive<EditPublicationRequest>()

                val newPublication = publicationsStorage.editPublication(publicationId, request.text)

                if (newPublication == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(newPublication)
                }
            } catch (ex: Exception) {
                println(ex.message)
            }
        }

        delete("${prefix}/{id}/delete") {
            try {
                val publicationId = call.parameters["id"]?.toLong() ?: 0

                val publication = publicationsStorage.deleteById(publicationId)

                if (publication == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(publication)
                }
            } catch (ex: Exception) {
                println(ex.message)
            }
        }

        put("${prefix}/create") {
            try {
                val request = call.receive<CreatePublicationRequest>()

                val createdPublication = publicationsStorage.createPublication(request.text)

                if (createdPublication == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    call.respond(createdPublication)
                }
            } catch (ex: Exception) {
                println(ex.message)
            }
        }


    }
}