package com.cinemas.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
    @Column
    private Integer SeatRows;
    @Column
    private Integer SeatColumns;
    @Column
    private Integer doubleSeatRows;
    @Column
    private Integer doubleSeatColumns;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    Cinema cinema;

}
