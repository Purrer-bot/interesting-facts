package org.purrer.facts.repo

import org.purrer.facts.model.Fact
import org.springframework.data.jpa.repository.JpaRepository

interface FactRepository : JpaRepository<Fact, Int> {

    fun findAllByRarity(rarity: String): List<Fact>
}