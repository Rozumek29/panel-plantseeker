package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.Plant;
import com.github.rozumek29.plantseekerpanel.data.service.PlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.crud.CrudGrid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;

@PageTitle("Plants")
@Route(value = "plants", layout = MainLayout.class)
@RolesAllowed("user")
public class PlantsView extends VerticalLayout {

    public PlantsView(PlantService service) {
        setSpacing(false);

        var crud = new GridCrud<>(Plant.class, service);
        crud.getGrid().setColumns("name", "latinName", "origin", "species", "family", "description", "img");
        crud.getCrudFormFactory().setVisibleProperties("name", "latinName", "origin", "species", "family", "description", "img");

        add(
                crud
        );

    }

}
