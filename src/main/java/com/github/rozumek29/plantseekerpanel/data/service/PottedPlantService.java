package com.github.rozumek29.plantseekerpanel.data.service;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PottedPlantService implements CrudListener<PottedPlant> {

    private final PottedPlantRepository repository;

    @Override
    public Collection<PottedPlant> findAll() {
        return repository.findAll();
    }

    @Override
    public PottedPlant add(PottedPlant plant) {
        return repository.save(plant);
    }

    @Override
    public PottedPlant update(PottedPlant plant) {
        return repository.save(plant);
    }

    @Override
    public void delete(PottedPlant plant) {
        repository.delete(plant);
    }

    public Long count(){
        return repository.count();
    }
}
