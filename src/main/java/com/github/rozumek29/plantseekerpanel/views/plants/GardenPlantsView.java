package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.service.GardenPlantRepository;
import com.github.rozumek29.plantseekerpanel.data.service.GardenPlantService;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;

@PageTitle("Garden Plants")
@Route(value = "garden-plants", layout = MainLayout.class)
@RolesAllowed("user")
public class GardenPlantsView extends VerticalLayout {

    public GardenPlantsView(GardenPlantService service) {
        setSpacing(false);

        var crud = new GridCrud<>(GardenPlant.class, service);
        crud.setAddOperationVisible(false);
        crud.getCrudLayout().addToolbarComponent(new Button("", new Icon(VaadinIcon.PLUS), event -> {
            UI.getCurrent().navigate(NewGardenPlant.class);
        }));

        add(
                crud
        );
    }
}
