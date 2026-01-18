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
        if (demigod.getAge() <= 0 || demigod.getAge() >= 100) {
            throw new RuntimeException("Invalid age for a Demigod.");
        }

        if(demigod.getName() == null || demigod.getName().isBlank()) {
            throw new RuntimeException("Every Demigod needs a name.");
        }

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
            destinationCabin = cabinRepository.findByGodIgnoreCase(demigod.getDivineRelative())
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

    public Demigod searchById(Long id) {
        return demigodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camper with ID " + id + " not found. Maybe it turned into a star?"));
    }

    public void expelOrBury(Long id) {
        Demigod camper = searchById(id);

        boolean onQuest = camper.getMissions().stream()
                .anyMatch(m -> !m.isComplete());

        if (onQuest) {
            throw new RuntimeException("ACTION DENIED: Camper " + camper.getName() + " is on a Quest! Cancel the Quest or wait for the return.");
        }

        demigodRepository.deleteById(id);
    }

    public List<Demigod> searchAll() {
        return demigodRepository.findAll();
    }

    public Demigod updateData(Long id, Demigod newData) {
        Demigod existing = searchById(id);

        existing.setName(newData.getName());
        existing.setAge(newData.getAge());
        existing.setGenre(newData.getGenre());

        return demigodRepository.save(existing);
    }
}