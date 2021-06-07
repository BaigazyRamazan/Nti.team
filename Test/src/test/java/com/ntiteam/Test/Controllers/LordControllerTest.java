package com.ntiteam.Test.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntiteam.Test.Lord.Lord;
import com.ntiteam.Test.Lord.LordController;
import com.ntiteam.Test.Lord.LordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(LordController.class)
public class LordControllerTest {

    @MockBean
    private LordService lordService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    private String route = "/api/lords";

    @Test
    public void testCreateLord() throws Exception{
        Lord caesar = new Lord();
        caesar.setLordId(1l);
        caesar.setName("Julius Caesar");
        caesar.setAge(55);

        String inputJson = this.mapToJson(caesar);

        when(lordService.createLord(any(Lord.class))).thenReturn(caesar);

        this.mockMvc.perform(post(route + "/create")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
    }

    @Test
    public void showLordsThatHaveNotPlanetsTest() throws Exception {
        Lord caesar = new Lord();
        caesar.setLordId(1l);
        caesar.setName("Julius Caesar");
        caesar.setAge(55);

        when(lordService.getBezdelniki()).thenReturn(List.of(caesar));

        this.mockMvc.perform(get(route + "/bezdelniki")).andReturn();
    }

    @Test
    public void showTenYoungestLordsTest() throws Exception{
        Lord caesar = new Lord("Julius Caesar",54);
        caesar.setLordId(1l);
        Lord alexander = new Lord("Alexander the Great",32);
        alexander.setLordId(2l);
        Lord genghisKhan = new Lord("Genghis Khan",29);
        genghisKhan.setLordId(3l);
        Lord shivaji = new Lord("Shivaji",49);
        shivaji.setLordId(4l);
        Lord augustus = new Lord("Augustus",48);
        augustus.setLordId(5l);
        Lord napoleon = new Lord("Napoleon Bonaparte",51);
        napoleon.setLordId(6l);
        Lord henry = new Lord("Henry VII",52);
        henry.setLordId(7l);
        Lord wilhelm = new Lord("Wilhelm II",82);
        wilhelm.setLordId(8l);
        Lord marcusAurelius = new Lord("Marcus Aurelius",58);
        marcusAurelius.setLordId(9l);
        Lord kublaiKhan = new Lord("Kublai Khan",78);
        kublaiKhan.setLordId(10l);
        Lord akbar = new Lord("Akbar",62);
        akbar.setLordId(11l);

        when(lordService.getTenYoungestLords()).thenReturn(
                List.of(genghisKhan,alexander,augustus,shivaji,napoleon,henry,caesar,marcusAurelius,akbar,kublaiKhan)
        );

        this.mockMvc.perform(get(route + "/10youngest")).andReturn();
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
