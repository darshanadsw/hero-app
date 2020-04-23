package com.darshana.demo.heroapp.repository

import com.darshana.demo.heroapp.model.Ability
import org.springframework.data.repository.CrudRepository

interface AbilityRepository extends CrudRepository<Ability,Long> {}
