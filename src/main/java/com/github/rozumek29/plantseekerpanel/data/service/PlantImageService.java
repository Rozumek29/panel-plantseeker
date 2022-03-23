package com.github.rozumek29.plantseekerpanel.data.service;

import com.github.rozumek29.plantseekerpanel.data.entity.PlantImage;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PlantImageService{

    @Autowired
    PlantImageRepository repository;

    public Collection<PlantImage> findAll() {
        return repository.findAll();
    }

    public PlantImage add(PlantImage plantImage) {
        return repository.save(plantImage);
    }

    public PlantImage update(PlantImage plantImage) {
        return repository.save(plantImage);
    }

    public void delete(PlantImage plantImage) {
        repository.delete(plantImage);
    }

    public List<PlantImage> findByPlanId(Long plantId) {
        return repository.getByPlantId(plantId);
    }


}
