package com.darshana.demo.heroapp.service

import com.darshana.demo.heroapp.model.Hero
import com.darshana.demo.heroapp.repository.HeroRepository
import org.springframework.stereotype.Service

import javax.persistence.EntityNotFoundException

@Service
class HeroService {

    HeroRepository heroRepository

    HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository
    }

    def findAllHeroes(){
        heroRepository.findAll().asList()
    }

    def findHeroById(id){
        heroRepository.findById(id).orElse(null)
    }

    def findHeroByIdOrError(def id){
        heroRepository.findById(id)
                .orElseThrow({->new EntityNotFoundException("No hero found with id $id")})
    }

    def saveHero(Hero hero){
        hero.abilities?.each {it.hero = hero}
        heroRepository.save(hero)
    }

    def updateHero(Hero hero){
        Hero persisted = findHeroById(hero.id)
        persisted.with {
            name = hero.name
        }
        def toBeRemoved = []
        persisted.abilities.each {
            def a = hero.abilities.find {it2 -> it2.name == it.name}
            if (a == null) toBeRemoved.add(it)
            else it.name = a.name
        }
        persisted.abilities.removeAll(toBeRemoved)
        hero.abilities.each {
            if(it.id == null){
                it.hero = persisted
                persisted.abilities.add(it)
            }
        }
        heroRepository.save(persisted)
    }

    def deleteHeroById(id){
        def hero = findHeroById(id)
        heroRepository.delete(hero)
        hero
    }

}
