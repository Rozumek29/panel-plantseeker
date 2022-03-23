package com.github.rozumek29.plantseekerpanel.data.service;

import com.github.rozumek29.plantseekerpanel.data.entity.PlantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantImageRepository extends JpaRepository<PlantImage, Long> {

    @Query(value = "SELECT * FROM plant_images WHERE plant_id = ?1", nativeQuery = true)
    public List<PlantImage> getByPlantId(Long plantId);

}
