package com.doriguelo.halfblood_camp_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cabin {
    @Id
    private Integer number;
    private String god;
    private int capacity;
    private boolean onlyWomen;

    public Cabin(Integer number, String god, int capacity, boolean onlyWomen) {
        this.number = number;
        this.god = god;
        this.capacity = capacity;
        this.onlyWomen = onlyWomen;
    }
}