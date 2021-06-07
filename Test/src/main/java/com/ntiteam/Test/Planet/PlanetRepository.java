package com.ntiteam.Test.Planet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
//    @Query("SELECT * FROM Planet WHERE name = ?1")
//    List<Planet> getPlanetByName(String name);
}
