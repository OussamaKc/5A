package org.polytech.covidapi.repositories;

import org.polytech.covidapi.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICenter extends JpaRepository<Center, Long> {
    List<Center> findAllByNameContaining(String name);
}
