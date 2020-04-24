package com.darshana.demo.heroapp.controller

import com.darshana.demo.heroapp.service.HeroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class HeroControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    HeroService heroService

    def "when hero is call return all heroes"() {
        expect:
        mockMvc.perform(MockMvcRequestBuilders.get("/hero"))
                .andExpect(MockMvcResultMatchers.status().isOk())
    }
}
