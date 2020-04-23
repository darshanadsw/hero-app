package com.darshana.demo.heroapp.service

import com.darshana.demo.heroapp.model.Disaster
import com.darshana.demo.heroapp.repository.DisasterRepository
import org.springframework.stereotype.Service

import javax.persistence.EntityNotFoundException

@Service
class DisasterService {

    DisasterRepository disasterRepository

    HeroService heroService

    DisasterService(DisasterRepository disasterRepository,HeroService heroService){
        this.disasterRepository = disasterRepository
        this.heroService = heroService
    }

    def findAllDisasters(){
        disasterRepository.findAll().asList()
    }

    def findById(id){
        disasterRepository.findById(id).orElse(null)
    }

    Disaster findByIdOrError(long id) {
        disasterRepository.findById(id).orElseThrow({
            new EntityNotFoundException()
        })
    }

    Disaster save(Disaster disaster) {
        disaster.isResolved = false
        disasterRepository.save(disaster)
    }

    Disaster update(Disaster disaster, long id) {
        def persisted = findByIdOrError(id)
        // update entity's values
        persisted.with {
            title = disaster.title
            location = disaster.location
            time = disaster.time
        }
        disasterRepository.save(persisted)
    }

    Disaster assignHero(long id, long heroId) {
        def disaster = findByIdOrError(id)
        def hero = heroService.findHeroByIdOrError(heroId)

        disaster.heroes.add(hero)
        disasterRepository.save(disaster)
    }

    Disaster removeHero(long id, long heroId) {
        def disaster = findByIdOrError(id)
        def hero = heroService.findHeroByIdOrError(heroId)

        disaster.heroes.remove(hero)
        disasterRepository.save(disaster)
    }

    Disaster resolve(long id) {
        def disaster = findByIdOrError(id)
        disaster.isResolved = true

        disasterRepository.save(disaster)
    }

    Disaster deleteById(long id) {
        def disaster = findByIdOrError(id)
        disasterRepository.delete(disaster)
        disaster
    }


}
