package com.doriguelo.halfblood_camp_api.repository;

import com.doriguelo.halfblood_camp_api.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CabinRepository extends JpaRepository<Cabin, Integer> {
    Optional<Cabin> findByGodIgnoreCase(String god);
}