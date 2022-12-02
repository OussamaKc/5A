package org.polytech.covidapi.repositories;

import org.polytech.covidapi.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICity extends JpaRepository<City,Long> {

    City findCityByName(String name);

}
