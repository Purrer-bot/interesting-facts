package org.purrer.facts.service

import org.purrer.facts.dto.FactDto
import org.purrer.facts.dto.Rarity
import org.purrer.facts.model.Fact
import org.purrer.facts.repo.FactRepository
import org.purrer.facts.service.validation.FactValidator
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class FactService(
    private val repository: FactRepository,
    private val validator: FactValidator,
    private val randomCalculator: RandomCalculator
) {

    @Transactional
    fun addFactAndUpdateProbabilities(factDto: FactDto): FactDto {
        validator.validate(factDto)
        val fact = Fact(rarity = factDto.rarity.getValue(), content = factDto.content)

        repository.save(fact)
        randomCalculator.updateProbabilities()

        return factDto
    }

    fun getRandomFact(): FactDto {
        val rarity = randomCalculator.getRandomRarity()
        val factList = repository.findAllByRarity(rarity = rarity.getValue())
        val fact = factList.random()

        return FactDto(Rarity.valueOf(fact.rarity.uppercase()), fact.content)
    }

    fun getAllFacts(): List<FactDto> {
        val facts = repository.findAll()
        return facts.map { FactDto(Rarity.valueOf(it.rarity.uppercase()), it.content) }
    }

    fun getFactByRarity(rarity: Rarity): List<FactDto> {
        val facts = repository.findAllByRarity(rarity.getValue())
        return facts.map { FactDto(Rarity.valueOf(it.rarity.uppercase()), it.content) }
    }
}