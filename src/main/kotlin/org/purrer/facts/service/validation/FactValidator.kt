package org.purrer.facts.service.validation

import org.purrer.facts.dto.FactDto

interface FactValidator {

    fun validate(factDto: FactDto)
}