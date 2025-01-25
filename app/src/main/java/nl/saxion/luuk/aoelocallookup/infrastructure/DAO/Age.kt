package nl.saxion.luuk.aoelocallookup.infrastructure.DAO

import kotlinx.serialization.Serializable

@Serializable
data class Age(
    val id: Int,
    val ageName: String,
    val units: List<Unit>,
    val buildings: List<Building>
)