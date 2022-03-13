package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.service.GardenPlantService;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Route(value = "new-garden-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class NewGardenPlant extends HorizontalLayout {

    private final TextField polishName = new TextField("Polish name");
    private final TextField latinName = new TextField("Latin name");
    private final TextField polishFamily = new TextField("Polish family");
    private final TextField latinFamily = new TextField("Latin family");
    private final TextField decorativeness = new TextField("Decorativeness");
    private final TextField plantUsage = new TextField("Plant Usage");
    private final TextArea description = new TextArea("description");

    private final TextField sort = new TextField("Sort");
    private final TextField height = new TextField("Height");
    private final TextField bark = new TextField("Bark");
    private final TextField shoots = new TextField("Shoots");
    private final TextField leaves = new TextField("leaves");
    private final TextField flowers_desc = new TextField("Flowers Description");
    private final DatePicker flowers_date = new DatePicker("Flowers Date");
    private final TextField fruits_desc = new TextField("Fruits Description");
    private final DatePicker fruits_date = new DatePicker("Fruits Date");
    private final TextField origin = new TextField("Origin");

    public NewGardenPlant(GardenPlantService service) {

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
                                new H2("Create Garden Plant"),
                                new FormLayout(
                                        polishName,
                                        latinName,
                                        polishFamily,
                                        latinFamily,

                                        sort,
                                        height,
                                        bark,
                                        shoots,
                                        leaves,
                                        flowers_desc,
                                        flowers_date,
                                        fruits_desc,
                                        fruits_date,

                                        origin,
                                        decorativeness,
                                        plantUsage,
                                        description
                                ),
                                new Button("Save", event -> {
                                    var plant = new GardenPlant();

                                    plant.setPolishName(polishName.getValue());
                                    plant.setLatinName(latinName.getValue());
                                    plant.setPolishFamily(polishFamily.getValue());
                                    plant.setLatinFamily(latinFamily.getValue());

                                    plant.setSort(sort.getValue());
                                    plant.setHeight(height.getValue());
                                    plant.setBark(bark.getValue());
                                    plant.setShoots(shoots.getValue());
                                    plant.setLeaves(leaves.getValue());
                                    plant.setFlowers_desc(flowers_desc.getValue());
                                    plant.setFlowers_date(flowers_date.getValue());
                                    plant.setFruits_desc(fruits_desc.getValue());
                                    plant.setFruits_date(fruits_date.getValue());

                                    plant.setOrigin(origin.getValue());
                                    plant.setDecorativeness(decorativeness.getValue());
                                    plant.setPlantUsage(plantUsage.getValue());

                                    plant.setDescription(description.getValue());
                                    List<String> imgList = new ArrayList<>();
                                    imgList.add("img_1.jpg");
                                    imgList.add("img_2.jpg");
                                    plant.setImgList(imgList);


                                    service.add(plant);
                                    UI.getCurrent().navigate(GardenPlantsView.class);
                                    Notification.show("Plant saved.");
                                })
                        )
                )

        );
    }

}
