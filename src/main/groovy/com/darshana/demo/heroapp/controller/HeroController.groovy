package com.darshana.demo.heroapp.controller

import com.darshana.demo.heroapp.model.Hero
import com.darshana.demo.heroapp.service.HeroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hero")
class HeroController {

    @Autowired
    HeroService heroService

    @GetMapping('')
    def listAll(){
        heroService.findAllHeroes()
    }

    @GetMapping('/{id}')
    def findById(@PathVariable long id){
        heroService.findHeroById(id)
    }
    @PostMapping('')
    def save(@RequestBody Hero hero){
        heroService.saveHero(hero)
    }

    @PutMapping('')
    def update(@RequestBody Hero hero){
        heroService.updateHero(hero)
    }

    @DeleteMapping('/{id}')
    def delete(@PathVariable long id){
        heroService.deleteHeroById(id)
    }
}
