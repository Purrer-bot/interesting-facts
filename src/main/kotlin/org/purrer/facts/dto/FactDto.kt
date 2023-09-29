package org.purrer.facts.dto

data class FactDto(val rarity: Rarity, val content: String) {
}

enum class Rarity(private val value: String) {
    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    LEGENDARY("legendary"),
    CELESTIAL("celestial");

    fun getValue():String{
        return value
    }
}