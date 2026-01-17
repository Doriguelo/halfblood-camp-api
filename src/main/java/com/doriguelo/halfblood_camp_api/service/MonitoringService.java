package com.doriguelo.halfblood_camp_api.service;

import com.doriguelo.halfblood_camp_api.model.Demigod;
import com.doriguelo.halfblood_camp_api.repository.DemigodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MonitoringService {
    @Autowired
    private DemigodRepository repository;

    @Scheduled(fixedRate = 20000)
    public void verifyFulfillmentOfThePromise() {
        List<Demigod> demigods = repository.findAll();
        boolean brokenPact = false;

        System.out.println("\n[SYSTEM] Verifying Oath on the River Styx...");

        for(Demigod d : demigods) {
           if(d.getAge() >= 13 && !d.isClaimed()) {
               System.out.println("WARNING: " + d.getName() + " (Cabin 11) is " + d.getAge() + " years old and has not yet been claimed!");
               System.out.println("-> Measure: Sending a messenger harpy to Olympus");
               brokenPact = true;
            }
        }

        if(!brokenPact) {
            System.out.println("Status: The pact has been upheld. All campers over the age of thirteen have been claimed.");
        }
    }

}
