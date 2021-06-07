package com.ntiteam.Test.Planet;

import com.ntiteam.Test.Lord.Lord;
import com.ntiteam.Test.Lord.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PlanetService {
    private final PlanetRepository planetRepository;

    @Autowired
    private LordService lordService;

    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }
//
//    public List<Planet> getPlanetByName(String name){
//         return planetRepository.getPlanetByName(name);
//    }

    public Planet createPlanet(String name,Long lordId) {
        Lord lord = lordService.getLordById(lordId);
        Planet planet = new Planet(name,lord);
        lord.addPlanet(planet);

        //Adding planet to repository
        planetRepository.save(planet);
        return planet;
    }

    public Lord changeLord(Long planetId, Long lordId) {
        Planet planet = planetRepository.findById(planetId).orElseThrow(
                () -> new IllegalStateException("Planet with id " + planetId + " doesn't exist"));
        Lord oldLord = planet.getLord();
        Lord newLord = lordService.getLordById(lordId);

        //If we have the same id with old lordId
        if (oldLord.getLordId() == lordId){
            return oldLord;
        }

        //Setting a new lord
        planet.setLord(newLord);
        lordService.getLordRepository().save(newLord);

        //Removing planet from old planet
        oldLord.getPlanets().remove(planet);
        lordService.getLordRepository().save(oldLord);

        return newLord;
    }

    public Planet deletePlanet(Long planetId) {
        Planet planet = planetRepository.findById(planetId).orElseThrow(
                () -> new IllegalStateException("Planet with id " + planetId + " doesn't exist"));
        Lord lord = planet.getLord();
        lord.getPlanets().remove(planet);

        //Deleting from repository
        planetRepository.deleteById(planetId);

        return planet;
    }
}
