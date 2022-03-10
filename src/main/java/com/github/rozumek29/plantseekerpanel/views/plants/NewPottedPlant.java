package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "new-potted-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class NewPottedPlant extends HorizontalLayout {

    private TextField polishName = new TextField("Polish Name");
    private TextField latinName = new TextField("Latin Name");

    public NewPottedPlant() {
        setSpacing(true);
        Button backbtn = new Button("Go back", new Icon(VaadinIcon.ARROW_BACKWARD));
        backbtn.addClickListener(event -> {
            UI.getCurrent().navigate(PlantsView.class);
        });

        Binder<PottedPlant> binder = new Binder<>();
        binder.bindInstanceFields(this);

        add(
                new HorizontalLayout(
                        backbtn,
                        new VerticalLayout(
                                new FormLayout(
                                        polishName,
                                        latinName
                                ),
                                new Button("Save", new Icon(VaadinIcon.DISC), event ->{
                                    var plant = new PottedPlant();
                                    binder.writeBeanIfValid(plant);
                                    Notification.show("Plant saved.");
                                    binder.removeBean();
                                })
                        )
                )
        );
    }

}
