package nl.saxion.luuk.aoelocallookup.infrastructure.DAO

import kotlinx.serialization.Serializable

@Serializable
data class Civilization(
    val id: Int,
    val civName: String,
    val units: List<Unit>,
    val techs: List<Tech>,
    val buildings: List<Building>
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

@Serializable
data class Building(
    val id: Int,
    val buildingName: String,
    val ageId: Int
)
