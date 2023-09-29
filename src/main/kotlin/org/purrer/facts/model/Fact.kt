package org.purrer.facts.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Fact(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
           val id: Int = 0,
           val rarity: String,

           @Column(name = "fact_content")
           val content: String) {
}