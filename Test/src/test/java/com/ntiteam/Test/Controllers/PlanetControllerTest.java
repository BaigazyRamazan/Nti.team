package com.ntiteam.Test.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntiteam.Test.Lord.Lord;
import com.ntiteam.Test.Planet.Planet;
import com.ntiteam.Test.Planet.PlanetController;
import com.ntiteam.Test.Planet.PlanetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {

    @MockBean
    private PlanetService planetService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;
    
    private String route = "/api/planets";

    @Test
    public void testCreatePlanet() throws Exception{
        Planet jupiter = new Planet();
        jupiter.setId(1l);
        jupiter.setName("Jupiter");

        when(planetService.createPlanet(any(String.class), any(Long.class))).thenReturn(jupiter);

        this.mockMvc.perform(post(route + "/create").contentType(MediaType.APPLICATION_JSON_VALUE).param("name","Jupiter")
                .param("lord_id","1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    }

    @Test
    public void changePlanetLordTest() throws Exception {
        Lord lord = new Lord();
        lord.setLordId(1l);
        lord.setAge(56);
        lord.setName("Julius Caesar");

        when(planetService.changeLord(anyLong(),anyLong())).thenReturn(lord);

        this.mockMvc.perform(put(route + "/change/{planet_id}/{lord_id}",1,1).accept("application/json"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
    }

    @Test
    public void deletePlanetTest() throws Exception{
        Planet jupiter = new Planet();
        jupiter.setId(1l);
        jupiter.setName("Jupiter");

        when(planetService.deletePlanet(anyLong())).thenReturn(jupiter);

        this.mockMvc.perform(delete(route + "/delete/{planet_id}",1)).andReturn();
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
