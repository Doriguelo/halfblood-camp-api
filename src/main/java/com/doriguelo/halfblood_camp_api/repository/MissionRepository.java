package com.doriguelo.halfblood_camp_api.repository;

import com.doriguelo.halfblood_camp_api.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository  extends JpaRepository<Mission, Long> {
}