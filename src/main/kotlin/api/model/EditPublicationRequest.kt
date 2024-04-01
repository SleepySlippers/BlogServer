package org.example.api.model

import kotlinx.serialization.Serializable

@Serializable
data class EditPublicationRequest(
    val text: String
)
