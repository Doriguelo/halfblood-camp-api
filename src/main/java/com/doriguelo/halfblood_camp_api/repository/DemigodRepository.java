package com.doriguelo.halfblood_camp_api.repository;

import com.doriguelo.halfblood_camp_api.model.Demigod;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemigodRepository extends JpaRepository<Demigod, Long> {
    List<Demigod> findByCabinNumber(Integer number);
}