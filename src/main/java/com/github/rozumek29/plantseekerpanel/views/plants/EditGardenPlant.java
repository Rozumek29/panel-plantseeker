package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.service.GardenPlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "edit-garden-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class EditGardenPlant extends VerticalLayout {

    public EditGardenPlant(GardenPlant plant, GardenPlantService service) {

    }
}
