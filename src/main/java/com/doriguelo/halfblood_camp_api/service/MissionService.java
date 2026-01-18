package com.doriguelo.halfblood_camp_api.service;

import com.doriguelo.halfblood_camp_api.model.Mission;
import com.doriguelo.halfblood_camp_api.model.Demigod;
import com.doriguelo.halfblood_camp_api.repository.MissionRepository;
import com.doriguelo.halfblood_camp_api.repository.DemigodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private DemigodRepository demigodRepository;

    public Mission createMission(Mission missionRequest) {
        if(missionRequest.getGroup() == null || missionRequest.getGroup().size() != 3) {
            throw new RuntimeException("AN ANCESTRAL RULE HAS BEEN BROKEN: A quest requires exactly three demigods.");
        }

        List<Demigod> confirmedGroup = new ArrayList<>();

        for(Demigod d : missionRequest.getGroup()) {
            Demigod existing = demigodRepository.findById(d.getId())
                    .orElseThrow(() -> new RuntimeException("Demigod with ID " + + d.getId() + " not found!"));
            confirmedGroup.add(existing);
        }

        missionRequest.setGroup(confirmedGroup);
        missionRequest.setComplete(false);

        return missionRepository.save(missionRequest);
    }

    public Mission completeMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found!"));
        mission.setComplete(true);

        return missionRepository.save(mission);
    }
}