package com.darshana.demo.heroapp.model

import groovy.transform.ToString
import groovy.transform.builder.Builder

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@ToString
@Entity
@Builder
class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String title

    String location

    Date time

    boolean isResolved = false

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = 'disaster_hero',
    joinColumns = @JoinColumn(name = 'disaster_id'),
    inverseJoinColumns = @JoinColumn(name = 'hero_id'))
    Set<Hero> heroes


}
