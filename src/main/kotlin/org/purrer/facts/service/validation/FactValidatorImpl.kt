package org.purrer.facts.service.validation

import org.purrer.facts.dto.FactDto
import org.purrer.facts.exception.ValidationException
import org.springframework.stereotype.Service

@Service
class FactValidatorImpl : FactValidator {

    override fun validate(factDto: FactDto) {
        if (factDto.content.isEmpty()) throw ValidationException("Content should not be empty!")
    }
}