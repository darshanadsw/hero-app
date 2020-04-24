package com.darshana.demo.heroapp.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Builder
@EqualsAndHashCode
@Entity
class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "hero")
    List<Ability> abilities

    @ManyToMany(mappedBy = 'heroes')
    Set<Disaster> disasters

}
