package com.github.rozumek29.plantseekerpanel.data.service;

import com.github.rozumek29.plantseekerpanel.data.entity.Plant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PlantService implements CrudListener<Plant> {

    private final PlantRepository repository;

    @Override
    public Collection<Plant> findAll() {
        return repository.findAll();
    }

    @Override
    public Plant add(Plant plant) {
        return repository.save(plant);
    }

    @Override
    public Plant update(Plant plant) {
        return repository.save(plant);
    }

    @Override
    public void delete(Plant plant) {
        repository.delete(plant);
    }

    public Long count(){
        return repository.count();
    }
}
