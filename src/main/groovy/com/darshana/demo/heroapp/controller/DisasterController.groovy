package com.darshana.demo.heroapp.controller

import com.darshana.demo.heroapp.model.Disaster
import com.darshana.demo.heroapp.service.DisasterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/disaster")
class DisasterController {

    @Autowired
    DisasterService disasterService

    @GetMapping
    def findAllDisasters(){
        disasterService.findAllDisasters()
    }

    @GetMapping('/{id}')
    def findDisasterById(@PathVariable long id){
        disasterService.findById(id)
    }

    @PostMapping
    def createDisaster(@RequestBody Disaster disaster){
        disasterService.save(disaster)
    }

    @PutMapping('/{id}')
    def updateDisaster(@RequestBody Disaster disaster,@PathVariable long id){
        disasterService.update(disaster,id)
    }

    @PostMapping('/{id}/hero/{heroId}')
    Disaster assignHero(@PathVariable long id, @PathVariable long heroId) {
        disasterService.assignHero(id, heroId)
    }

    @DeleteMapping('/{id}/hero/{heroId}')
    Disaster removeHero(@PathVariable long id, @PathVariable long heroId) {
        disasterService.removeHero(id, heroId)
    }

    @PostMapping('/{id}')
    Disaster resolve(@PathVariable long id) {
        disasterService.resolve(id)
    }

    @DeleteMapping('/{id}')
    Disaster deleteById(@PathVariable long id) {
        disasterService.deleteById(id)
    }

}
