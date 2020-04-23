package com.darshana.demo.heroapp.repository

import com.darshana.demo.heroapp.model.Hero
import org.springframework.data.repository.CrudRepository

interface HeroRepository extends CrudRepository<Hero,Long> {}
