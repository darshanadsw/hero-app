package com.darshana.demo.heroapp.service

import com.darshana.demo.heroapp.model.Ability
import com.darshana.demo.heroapp.model.Hero
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.EntityNotFoundException
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@SpringBootTest
@Transactional
class HeroServiceTest extends Specification {

    @Autowired
    HeroService heroService

    @PersistenceContext
    EntityManager em

    def "FindAllHeroes"() {
        given:
        def hero = Hero.builder().name('hero 1').build()
        em.persist(hero)
        def fly = Ability.builder().name('fly').hero(hero).build()
        em.persist(fly)
        def swim = Ability.builder().name('swim').hero(hero).build()
        em.persist(swim)
        when:
        def heroes = heroService.findAllHeroes()
        then:
        heroes != null
        heroes.size() == 1
        heroes[0].name == 'hero 1'
    }

    def "find Hero by id" () {
        given:
        def hero = Hero.builder().name('hero 1').build()
        em.persist(hero)
        def fly = Ability.builder().name('fly').hero(hero).build()
        def swim = Ability.builder().name('swim').hero(hero).build()
        em.persist(fly)
        em.persist(swim)
        when:
        def result = heroService.findHeroById(hero.id)
        then:
        result.name == 'hero 1'

    }

    def "find hero by id when no result found"() {
        when:
        heroService.findHeroByIdOrError(1L)
        then:
        EntityNotFoundException e = thrown()
        e.message == 'No hero found with id 1'
    }

    def 'save new hero'() {
        when:
        def fly = Ability.builder().name('fly').build()
        def swim = Ability.builder().name('swim').build()
        def hero = Hero.builder().name('hero 1').abilities([fly,swim]).build()
        heroService.saveHero(hero)
        then:
        hero.id != null
    }

    def 'delete hero by id'() {
        given:
        def hero = Hero.builder().name('hero 1').build()
        em.persist(hero)
        def fly = Ability.builder().name('fly').hero(hero).build()
        def swim = Ability.builder().name('swim').hero(hero).build()
        em.persist(fly)
        em.persist(swim)
        when:
        heroService.deleteHeroById(hero.id)
        then:
        em.find(Hero.class,hero.id) == null
    }
}
