package com.github.wirye.anilibriakt.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

@Serializable
enum class Collections {
    PLANNED, WATCHED, WATCHING, POSTPONED, ABANDONED
}

@Serializable(with = CollectionSerializer::class)
data class Collection(
    val releaseId: Long,
    val label: String
) {
    val collection: Collections?
        get() = when (label) {
            "PLANNED" -> Collections.PLANNED
            "WATCHED" -> Collections.WATCHED
            "WATCHING" -> Collections.WATCHING
            "POSTPONED" -> Collections.POSTPONED
            "ABANDONED" -> Collections.ABANDONED
            else -> null
        }
}

internal object CollectionSerializer : KSerializer<Collection> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Collection")

    override fun deserialize(decoder: Decoder): Collection {
        val json = decoder as? JsonDecoder
            ?: error("Collection можно десериализовать только из JSON")
        val array = json.decodeJsonElement().jsonArray

        return Collection(
            releaseId = array[0].jsonPrimitive.long,
            label = array[1].jsonPrimitive.content
        )
    }

    override fun serialize(encoder: Encoder, value: Collection) {
        val json = encoder as? JsonEncoder
            ?: error("Collection можно сериализовать только в JSON")
        json.encodeJsonElement(
            buildJsonArray {
                add(value.releaseId)
                add(value.label)
            }
        )
    }
}