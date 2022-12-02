package org.polytech.covidapi.repositories;

import org.polytech.covidapi.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMeeting extends JpaRepository<Meeting,Long> {

    List<Meeting> findAllByUserIdOrderByAppointmentDesc(Long id);
}
