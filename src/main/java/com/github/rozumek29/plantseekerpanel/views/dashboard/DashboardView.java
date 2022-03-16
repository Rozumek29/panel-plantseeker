package com.github.rozumek29.plantseekerpanel.views.dashboard;

import com.github.rozumek29.plantseekerpanel.data.service.GardenPlantService;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import javax.annotation.security.PermitAll;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout {

    public DashboardView(GardenPlantService gardenService, PottedPlantService pottedService) {
        setSpacing(false);

        Span potted = new Span("Doniczkowych: "+pottedService.count());
        potted.getElement().getThemeList().add("badge");
        potted.getStyle().set("padding", "10px");
        potted.getStyle().set("font-size", "20px");

        Span garden = new Span("Ogrodowych: "+gardenService.count());
        garden.getElement().getThemeList().add("badge");
        garden.getStyle().set("padding", "10px");
        garden.getStyle().set("font-size", "20px");

        add(
                new HorizontalLayout(potted, garden)
        );

    }

}
