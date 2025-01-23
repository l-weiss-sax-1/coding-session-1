package nl.saxion.luuk.aoelocallookup.infrastructure.DAO

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Civilization(
    val id: Int,
    val civName: String,
    val units: List<Unit>,
    val techs: List<Tech>
)

@Serializable
data class Unit(
    val id: Int,
    val unitName: String,
    val ageId: Int


)

@Serializable
data class Tech(
    val id :Int,
    val techName: String,
    val ageId: Int
)

