package org.polytech.covidapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(unique = true)
    String name;
    Integer zipCode;
    @OneToMany(mappedBy = "city")
    @JsonIgnoreProperties("users")
    List<Center> centres;

    public City(String name, Integer zipCode){
        this.name = name;
        this.zipCode = zipCode;
    }

}
