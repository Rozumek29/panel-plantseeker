package com.github.rozumek29.plantseekerpanel.data.service;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.Optional;

@Service
public class PottedPlantService implements CrudListener<PottedPlant> {

    @Autowired
    private PottedPlantRepository repository;

    @Override
    public Collection<PottedPlant> findAll() {
        return repository.findAll();
    }

    public Optional<PottedPlant> findById(Long id){
        return repository.findById(id);
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
