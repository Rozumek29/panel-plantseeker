package com.github.rozumek29.plantseekerpanel.data.service;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GardenPlantService implements CrudListener<GardenPlant> {

    private final GardenPlantRepository repository;

    @Override
    public Collection<GardenPlant> findAll() {
        return repository.findAll();
    }

    @Override
    public GardenPlant add(GardenPlant plant) {
        return repository.save(plant);
    }

    @Override
    public GardenPlant update(GardenPlant plant) {
        return repository.save(plant);
    }

    @Override
    public void delete(GardenPlant plant) {
        repository.delete(plant);
    }

    public Long count(){
        return repository.count();
    }
}
