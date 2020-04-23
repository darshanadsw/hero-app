package com.darshana.demo.heroapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.ToString
import groovy.transform.builder.Builder

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
@Builder
@ToString
class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    Hero hero

}
