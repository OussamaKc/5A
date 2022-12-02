package org.polytech.covidapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.polytech.covidapi.user.domain.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Meeting {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @ManyToOne
    Center center;
    @ManyToOne
    @JsonIgnoreProperties("meetings")
    User user;
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate appointment;
    @Column(name = "is_done")
    Boolean is_done = false;


}
