package org.purrer.facts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FactsApplication

fun main(args: Array<String>) {
	runApplication<FactsApplication>(*args)
}
