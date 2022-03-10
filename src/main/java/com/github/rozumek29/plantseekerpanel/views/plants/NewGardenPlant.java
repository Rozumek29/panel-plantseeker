package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "new-garden-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class NewGardenPlant extends HorizontalLayout {

    public NewGardenPlant() {
        setSpacing(true);
        Button backbtn = new Button("Go back", new Icon(VaadinIcon.ARROW_BACKWARD));;
        backbtn.addClickListener(event -> {
            UI.getCurrent().navigate(PlantsView.class);
        });
        add(
            backbtn
        );
    }

}
