package com.doriguelo.halfblood_camp_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String oracle;
    private boolean complete;

    @ManyToMany
    @JoinTable(
            name = "mission_group",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "demigod_id")
    )
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("missions")
    private List<Demigod> group;
}