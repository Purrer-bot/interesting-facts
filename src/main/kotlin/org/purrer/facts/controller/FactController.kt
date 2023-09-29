package org.purrer.facts.controller

import org.purrer.facts.dto.FactDto
import org.purrer.facts.dto.Rarity
import org.purrer.facts.service.FactService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class FactController(val factService: FactService) {

    @GetMapping("/facts")
    fun getAllFacts(): ResponseEntity<List<FactDto>> {
        return ResponseEntity.ok(
            factService.getAllFacts()
        )
    }

    @GetMapping("/facts/random")
    fun getRandomFact(): ResponseEntity<FactDto> {
        return ResponseEntity.ok(factService.getRandomFact())
    }

    @PostMapping("/facts")
    fun addFact(@RequestBody factDto: FactDto): ResponseEntity<FactDto> {
        factService.addFactAndUpdateProbabilities(factDto)
        return ResponseEntity.ok(factDto)
    }

    @GetMapping("/facts/findByRarity")
    fun getFactsByRarity(@RequestParam rarity: Rarity): ResponseEntity<List<FactDto>> {
        return ResponseEntity.ok(factService.getFactByRarity(rarity))
    }
}