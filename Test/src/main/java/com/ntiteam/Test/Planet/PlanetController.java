package com.ntiteam.Test.Planet;

import com.ntiteam.Test.Lord.Lord;
import com.ntiteam.Test.Planet.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/planets")
public class PlanetController {

    private final PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping
    public List<Planet> getPlanets(){
        return planetService.getPlanets();
    }

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public Planet createPlanet(@RequestParam(name = "name") String planetName,
                             @RequestParam(name = "lord_id") String lordId){
        Planet planet = planetService.createPlanet(planetName,Long.valueOf(lordId));

        return planet;
    }

    @PutMapping(path = "/change/{planet_id}/{lord_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Lord changeLord(@PathVariable(name = "planet_id") Long planetId,
                           @PathVariable(name = "lord_id") Long lordId) {
        Lord lord = planetService.changeLord(planetId,lordId);

        return lord;
    }

    @DeleteMapping(path = "/delete/{planet_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Planet deletePlanet(@PathVariable(name = "planet_id") Long planetId){
        Planet planet = planetService.deletePlanet(planetId);

        return planet;
    }
}
