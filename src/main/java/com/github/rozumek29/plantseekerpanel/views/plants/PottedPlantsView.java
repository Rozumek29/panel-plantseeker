package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
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

@PageTitle("Potted Plants")
@Route(value = "potted-plants", layout = MainLayout.class)
@RolesAllowed("user")
public class PottedPlantsView extends VerticalLayout {

    public PottedPlantsView(PottedPlantService service) {
        setSpacing(false);

        var crud = new GridCrud<>(PottedPlant.class, service);
        crud.setAddOperationVisible(false);
        crud.getCrudLayout().addToolbarComponent(new Button("", new Icon(VaadinIcon.PLUS), event -> UI.getCurrent().navigate(NewPottedPlant.class)));
        crud.getGrid().setColumns("id", "polishName", "latinName", "polishFamily", "latinFamily", "decorativeness", "plantUsage", "toxicity", "lightConditions", "subsoil", "watering", "images", "description");

        add(
                crud
        );
    }
}
