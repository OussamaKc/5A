package org.polytech.covidapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.polytech.covidapi.user.domain.User;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    @ManyToOne()
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties("centres")
    City city;
    String address;
    String phone;
    String email;


    public Center(String name, City city) {
        this.name = name;
        this.city = city;
    }
}
