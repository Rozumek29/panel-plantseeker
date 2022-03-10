package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.Plant;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.service.PlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.crud.CrudGrid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
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

        crud.setHeightFull();
        crud.setAddOperationVisible(false);
        crud.setAddOperationVisible(false);

        MenuBar menu = new MenuBar();
        MenuItem item = menu.addItem("Add Plant");
        SubMenu subMenu = item.getSubMenu();

        subMenu.addItem("Garden Plant", clickEvent -> {
            UI.getCurrent().navigate(NewGardenPlant.class);
        });

        subMenu.addItem("Potted Plant", clickEvent -> {
            UI.getCurrent().navigate(NewPottedPlant.class);
        });

        crud.getCrudLayout().addToolbarComponent(menu);

        add(
                crud
        );

    }

}
