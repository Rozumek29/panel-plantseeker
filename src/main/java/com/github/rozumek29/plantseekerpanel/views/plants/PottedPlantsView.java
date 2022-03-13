package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;

@PageTitle("Potted Plants")
@Route(value = "potted-plants", layout = MainLayout.class)
@RolesAllowed("user")
public class PottedPlantsView extends VerticalLayout {

    public PottedPlantsView(PottedPlantService service) {
        setSpacing(false);

        var crud = new GridCrud<>(PottedPlant.class, service);

        add(
                crud
        );
    }
}
