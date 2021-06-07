package com.ntiteam.Test.Planet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ntiteam.Test.Lord.Lord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
public class Planet {
    
    @Id
    @SequenceGenerator(
            name = "planet_sequence",
            sequenceName = "planet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "planet_sequence"
    )
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lord_id")
    @JsonBackReference
    private Lord lord;

    public Planet(String name, Lord lord) {
        this.name = name;
        this.lord = lord;
    }

    public Planet(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lordId=" + lord.getLordId() +
                '}';
    }
}
