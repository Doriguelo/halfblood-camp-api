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

    @GetMapping("/{id}")
    public ResponseEntity<?> searchCamper(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.searchById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCamper(@PathVariable Long id) {
        try {
            service.expelOrBury(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(service.searchAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCamper(@PathVariable Long id, @RequestBody Demigod demigod) {
        try {
            return ResponseEntity.ok(service.updateData(id, demigod));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}