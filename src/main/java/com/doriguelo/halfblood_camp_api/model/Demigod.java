package com.doriguelo.halfblood_camp_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Demigod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String genre;
    private String divineRelative;
    private boolean claimed;
    private String dangerousLevel;
    private int yearsAtCamp;

    @ManyToOne
    @JoinColumn(name = "cabin_number")
    private Cabin cabin;

    @ManyToMany(mappedBy = "group")
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("group")
    private java.util.List<Mission> missions;
}