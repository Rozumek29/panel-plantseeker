package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PlantImage;
import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.service.PlantImageService;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.ftp.FTPUploader;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.spring.annotation.SpringComponent;
import elemental.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class EditPottedPlant {

    private final TextField polishName = new TextField("Nazwa Polska");
    private final TextField latinName = new TextField("Nazwa Łacińska");
    private final TextField polishFamily = new TextField("Polska nazwa rodziny");
    private final TextField latinFamily = new TextField("Łacińska nazwa rodziny");
    private final TextField decorativeness = new TextField("Dekoracyjność");
    private final TextField plantUsage = new TextField("Wykorzystanie");
    private final TextArea description = new TextArea("Opis");

    private final TextField toxicity = new TextField("Toksyczność");
    private final TextField lightConditions = new TextField("Warunki świetlne");
    private final TextField subsoil = new TextField("Podłoże");
    private final TextField watering = new TextField("Podlewanie");

    private MultiFileMemoryBuffer buffer;

    private final H4 uploadTitle = new H4("Zdjęcia");
    private final Paragraph uploadHint = new Paragraph("Akceptowane formaty: JPEG, PNG");
    private Upload imgUpload;

    Grid<PlantImage> grid;

    VerticalLayout imgLayout1;
    VerticalLayout imgLayout2;
    HorizontalLayout imgLayout;

    private PlantImageService imgService;

    public EditPottedPlant(PlantImageService imageService) {
        this.imgService = imageService;
    }

    private List<PlantImage> images;

    public VerticalLayout createDialogLayout(PottedPlantService service, Dialog dialog, Long plantID) {

        this.grid = new Grid<>(PlantImage.class);
        this.buffer = new MultiFileMemoryBuffer();
        this.imgUpload = new Upload(buffer);
        this.imgLayout1 = new VerticalLayout(uploadTitle, uploadHint, imgUpload);
        this.imgLayout2 = new VerticalLayout(grid);
        this.imgLayout = new HorizontalLayout(imgLayout1, imgLayout2);

        dialog.getElement().setAttribute("aria-label", "Edytuj rośinę");

        PottedPlant plant = service.findById(plantID).get();

        this.images = plant.getImages();

        H2 headLine = new H2("Edytuj roślinę");

        description.setWidth("100%");
        description.setHeight("50%");

        polishName.setValue(plant.getPolishName());
        latinName.setValue(plant.getLatinName());
        polishFamily.setValue(plant.getPolishFamily());
        latinFamily.setValue(plant.getPolishFamily());
        lightConditions.setValue(plant.getLightConditions());
        subsoil.setValue(plant.getSubsoil());
        watering.setValue(plant.getWatering());
        decorativeness.setValue(plant.getDecorativeness());
        plantUsage.setValue(plant.getPlantUsage());
        toxicity.setValue(plant.getToxicity());
        description.setValue(plant.getDescription());

        grid.addColumn(
                new ComponentRenderer<>(Button::new, ((button, plantImage) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(event -> {
                        imgService.delete(plantImage);
                        FTPUploader.remove(plantImage.getImg_name());
                        refreshGrid(plantImage.getPlant_id());
                        System.out.println(plant.getImages());

                        for (PlantImage img : plant.getImages()) {
                            if (img.getPlant_id() == plantImage.getPlant_id()){
                                plant.getImages().remove(img);
                            }
                        }
                        System.out.println(plant.getImages());
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                }))
        );

        refreshGrid(plantID);


        imgLayout.setWidth("100%");
        imgLayout1.setWidth("40%");
        imgLayout2.setWidth("60%");


        VerticalLayout verticalLayout = new VerticalLayout(
                headLine,
                new HorizontalLayout(
                        polishName,
                        latinName,
                        polishFamily,
                        latinFamily,
                        lightConditions
                ),
                new HorizontalLayout(
                        subsoil,
                        watering,
                        decorativeness,
                        plantUsage,
                        toxicity
                ),
                description,
                imgLayout,

                new Button("Edytuj", event -> {
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

                    plant.setPublished(LocalDate.now(ZoneId.of("Europe/Warsaw")));

                    if (imgUpload.isUploading()) {
                        return;
                    }

                    FTPUploader.reuploadImage(buffer, plant);
                    service.update(plant);

                    UI.getCurrent().navigate(PottedPlantsView.class);
                    Notification.show("Roślina została edytowana.");
                    imgUpload.getElement().setPropertyJson("files", Json.createArray());
                    dialog.close();
                })

        );

        return verticalLayout;
    }

    private void refreshGrid(Long plantID){
        grid.setItems(imgService.findByPlanId(plantID));
    }
}
