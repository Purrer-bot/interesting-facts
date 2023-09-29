package org.purrer.facts.service

import org.purrer.facts.dto.Rarity
import org.purrer.facts.repo.FactRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class RandomCalculator(private val repository: FactRepository) {

    private var map: MutableMap<Rarity, Double> = mutableMapOf()

    @PostConstruct
    private fun init() {
        val facts = repository.findAll()
        for (fact in facts) {
            val rarity = Rarity.valueOf(fact.rarity.uppercase())
            if (map.containsKey(rarity)) {
                val counter = map[rarity]?.plus(1.0)
                map[rarity] = counter ?: 0.0
            }
            map.putIfAbsent(rarity, 1.0)
        }

        for (entry in map) {
            entry.setValue(Math.round((entry.value / facts.size) * 100.0) / 100.0)
        }
        map = map.toList()
            .sortedBy { (_, value) -> value }
            .toMap().toMutableMap()
        println()
    }

    fun updateProbabilities() {
        init()
    }

    fun getRandomRarity(): Rarity{
        val random = Random().nextDouble()

        for (entry in map) {
            if(entry.value >= random) return entry.key
        }

        return Rarity.COMMON
    }
}