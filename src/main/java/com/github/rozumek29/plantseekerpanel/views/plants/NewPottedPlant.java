package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "new-potted-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class NewPottedPlant extends HorizontalLayout {

    private TextField polishName = new TextField("Polish name");
    private TextField latinName = new TextField("Latin name");
    private TextField polishFamily = new TextField("Polish family");
    private TextField latinFamily = new TextField("Latin family");
    private TextField decorativeness = new TextField("Decorativeness");
    private TextField plantUsage = new TextField("Plant Usage");
    private TextArea description = new TextArea("description");

    private TextField toxicity = new TextField("Toxicity");
    private TextField lightConditions = new TextField("Light Conditions");
    private TextField subsoil = new TextField("Subsoil");
    private TextField watering = new TextField("Watering");

    public NewPottedPlant(PottedPlantService service) {

        /*
        Yeah, I know. I should use binder here.
         */

        setSpacing(false);

        Button backbtn = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        backbtn.addClickListener(event -> {
            //UI.getCurrent().navigate(PlantsView.class);
        });

        add(
                new HorizontalLayout(
                        new VerticalLayout(
                                backbtn,
                                new H2("Create Potted Plant"),
                                new FormLayout(
                                        polishName,
                                        latinName,
                                        polishFamily,
                                        latinFamily,
                                        lightConditions,
                                        subsoil,
                                        watering,
                                        decorativeness,
                                        plantUsage,
                                        toxicity,
                                        description
                                ),
                                new Button("Save", event -> {
                                    var plant = new PottedPlant();

                                    plant.setPolishName(polishName.getValue());
                                    plant.setLatinName(latinName.getValue());
                                    plant.setPolishFamily(polishFamily.getValue());
                                    plant.setLatinFamily(latinFamily.getValue());
                                    plant.setLightConditions(lightConditions.getValue());
                                    plant.setSubsoil(subsoil.getValue());
                                    plant.setWatering(watering.getValue());
                                    plant.setDecorativeness(decorativeness.getValue());
                                    plant.setPlantUsage(plantUsage.getValue());
                                    plant.setToxicity(toxicity.getValue());
                                    plant.setDescription(description.getValue());

                                    service.add(plant);
                                    //UI.getCurrent().navigate(PlantsView.class);
                                    Notification.show("Plant saved.");
                                })
                        )
                )
        );
    }

}
