package com.github.rozumek29.plantseekerpanel.data.service;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenPlantRepository extends JpaRepository<GardenPlant, Long> {
}
