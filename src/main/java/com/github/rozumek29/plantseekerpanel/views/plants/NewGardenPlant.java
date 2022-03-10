package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.service.PlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "new-garden-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class NewGardenPlant extends HorizontalLayout {

    private TextField polishName = new TextField("Polish name");
    private TextField latinName = new TextField("Latin name");
    private TextField polishFamily = new TextField("Polish family");
    private TextField latinFamily = new TextField("Latin family");
    private TextField lightConditions = new TextField("Light Conditions");
    private TextField subsoil = new TextField("Subsoil");
    private TextField watering = new TextField("Watering");
    private TextField sort = new TextField("Sort");
    private TextField height = new TextField("Height");
    private TextField bark = new TextField("Bark");
    private TextField shoots = new TextField("Shoots");
    private TextField leaves = new TextField("leaves");
    private TextArea description = new TextArea("description");

    public NewGardenPlant(PlantService service) {

        /*
        Yeah, I know. I should use binder here.
         */

        setSpacing(false);
        Button backbtn = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        ;
        backbtn.addClickListener(event -> {
            UI.getCurrent().navigate(PlantsView.class);
        });
        add(
                new HorizontalLayout(
                        backbtn,
                        new FormLayout(
                                polishName,
                                latinName,
                                polishFamily,
                                latinFamily,
                                lightConditions,
                                subsoil,
                                watering,
                                sort,
                                height,
                                bark,
                                shoots,
                                leaves
                        ),
                        new Button("Save", event -> {
                            var plant = new GardenPlant();

                            plant.setPolishName(polishName.getValue());
                            plant.setLatinName(latinName.getValue());
                            plant.setPolishFamily(polishFamily.getValue());
                            plant.setLatinFamily(latinFamily.getValue());
                            plant.setLightConditions(lightConditions.getValue());
                            plant.setSubsoil(subsoil.getValue());
                            plant.setWatering(watering.getValue());
                            plant.setSort(sort.getValue());
                            plant.setHeight(height.getValue());
                            plant.setBark(bark.getValue());
                            plant.setShoots(shoots.getValue());
                            plant.setLeaves(leaves.getValue());
                            plant.setDescription(description.getValue());

                            service.add(plant);
                            UI.getCurrent().navigate(PlantsView.class);
                            Notification.show("Plant saved.");
                        })
                )

        );
    }

}
