package com.doriguelo.halfblood_camp_api.controller;

import com.doriguelo.halfblood_camp_api.model.Mission;
import com.doriguelo.halfblood_camp_api.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {
    @Autowired
    private MissionService service;

    @PostMapping
    public ResponseEntity<?> startMission(@RequestBody Mission mission) {
        try {
            return ResponseEntity.ok(service.createMission(mission));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}