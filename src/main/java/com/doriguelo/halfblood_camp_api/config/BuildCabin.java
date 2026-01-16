package com.doriguelo.halfblood_camp_api.config;

import com.doriguelo.halfblood_camp_api.model.Cabin;
import com.doriguelo.halfblood_camp_api.repository.CabinRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class BuildCabin {
    @Bean
    public CommandLineRunner initDatabase(CabinRepository repository) {
        return args -> {
            if(repository.count() == 0) {
                repository.saveAll(List.of(
                        new Cabin(1, "Zeus", 2, false),
                        new Cabin(2, "Hera", 0, false),
                        new Cabin(3, "Poseidon", 2, false),
                        new Cabin(4, "Demeter", 20, false),
                        new Cabin(5, "Ares", 25, false),
                        new Cabin(6, "Athena", 30, false),
                        new Cabin(7, "Apollo", 40, false),
                        new Cabin(8, "Artemis", 30, true),
                        new Cabin(9, "Hephaestus", 20, false),
                        new Cabin(10, "Aphrodite", 35, false),
                        new Cabin(11, "Hermes", 60, false),
                        new Cabin(12, "Dionysus", 5, false),
                        new Cabin(13, "Hades", 2, false),
                        new Cabin(14, "Iris", 15, false),
                        new Cabin(15, "Hypnos", 10, false),
                        new Cabin(16, "Nemesis", 10, false),
                        new Cabin(17, "Nike", 12, false),
                        new Cabin(18, "Hebe", 15, false),
                        new Cabin(19, "Tyche", 10, false),
                        new Cabin(20, "Hecate", 20, false)
                ));
                System.out.println("CAMP UPDATED: All 20 cabins built!");
            }
        };
    }
}