package com.darshana.demo.heroapp.repository

import com.darshana.demo.heroapp.model.Disaster
import org.springframework.data.repository.CrudRepository

interface DisasterRepository extends CrudRepository<Disaster,Long> {}
