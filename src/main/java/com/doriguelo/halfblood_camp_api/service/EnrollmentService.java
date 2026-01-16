package com.doriguelo.halfblood_camp_api.service;

import com.doriguelo.halfblood_camp_api.model.Cabin;
import com.doriguelo.halfblood_camp_api.model.Demigod;
import com.doriguelo.halfblood_camp_api.repository.CabinRepository;
import com.doriguelo.halfblood_camp_api.repository.DemigodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private DemigodRepository demigodRepository;
    @Autowired
    private CabinRepository cabinRepository;

    public Demigod enrollACamper(Demigod demigod) {

        if(demigod.getDivineRelative() == null || demigod.getDivineRelative().isEmpty()) {
            demigod.setDivineRelative("Undetermined");
            demigod.setClaimed(false);
        } else {
            demigod.setClaimed(true);
        }

        if(List.of("Zeus", "Poseidon", "Hades").contains(demigod.getDivineRelative())) {
            demigod.setDangerousLevel("HIGH - Continuously Monitor");
        } else {
            demigod.setDangerousLevel("Regular");
        }

        Cabin destinationCabin;

        if(demigod.getDivineRelative().equals("Undetermined")) {
            destinationCabin = cabinRepository.findById(11)
                    .orElseThrow(() -> new RuntimeException("Hermes cabin not found!"));
        } else {
            destinationCabin = cabinRepository.findByGod(demigod.getDivineRelative())
                    .orElseThrow(() -> new RuntimeException("We haven't built a cabin for " + demigod.getDivineRelative() + " yet."));
        }

        if(destinationCabin.getNumber() == 8 && !"F".equalsIgnoreCase(demigod.getGenre())) {
            throw new RuntimeException("FORBIDDEN: Only women may enter Artemis Chalet.");
        }

        int currentQty = demigodRepository.findByCabinNumber(destinationCabin.getNumber()).size();
        if(currentQty >= destinationCabin.getCapacity()) {
            if (destinationCabin.getNumber() != 11) {
                throw new RuntimeException("Cabin " + destinationCabin.getGod() + " is full!");
            } else {
                System.out.println("WARNING: Hermes cabin is overcrowded. Sleeping bag provided.");
            }
        }

        demigod.setCabin(destinationCabin);
        return demigodRepository.save(demigod);
    }

    public Demigod claimDemigod(Long IdDemigod, String godName) {
        Demigod d = demigodRepository.findById(IdDemigod).orElseThrow();
        d.setDivineRelative(godName);
        return enrollACamper(d);
    }
}