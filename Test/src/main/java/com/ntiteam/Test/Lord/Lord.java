package com.ntiteam.Test.Lord;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ntiteam.Test.Planet.Planet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
public class Lord {
    @Id
    @SequenceGenerator(
            name = "lord_sequence",
            sequenceName = "lord_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lord_sequence"
    )
    private Long lordId;
    private String name;
    private Integer age;

    @OneToMany(
            mappedBy = "lord",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Planet> planets = new ArrayList<>();

    public Lord(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void addPlanet(Planet planet){
        planets.add(planet);
        planet.setLord(this);
    }
}
