package com.doriguelo.halfblood_camp_api.controller;

import com.doriguelo.halfblood_camp_api.model.Demigod;
import com.doriguelo.halfblood_camp_api.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/camp")
public class CampController {
    @Autowired
    private EnrollmentService service;
    @PostMapping("/enter")
    public ResponseEntity<?> register(@RequestBody Demigod demigod) {
        try {
            Demigod newbie = service.enrollACamper(demigod);
            return ResponseEntity.ok(newbie);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/claim/{id}")
    public ResponseEntity<?> claimSon(@PathVariable Long id, @RequestParam String divineParentage) {
        try {
            return ResponseEntity.ok(service.claimDemigod(id, divineParentage));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}