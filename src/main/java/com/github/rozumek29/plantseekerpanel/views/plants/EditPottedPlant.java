package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.User;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.ftp.FTPUploader;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
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
import com.vaadin.flow.router.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;

@PageTitle("Edycja rośliny doniczkowej")
@Route(value = "edit-pooted-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class EditPottedPlant extends VerticalLayout implements HasUrlParameter<Long>{

    private Long plantID;

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

    private final MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();

    private final H4 uploadTitle = new H4("Zdjęcia");
    private final Paragraph uploadHint = new Paragraph("Akceptowane formaty: JPEG, PNG");
    private final Upload imgUpload = new Upload(buffer);

    H2 test = new H2();

    @Autowired
    PottedPlantService service;

    public EditPottedPlant(){

        Button backbtn = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        backbtn.addClickListener(event -> {
            UI.getCurrent().navigate(PottedPlantsView.class);
        });

        add(
                new HorizontalLayout(
                        new VerticalLayout(
                                backbtn,
                                new H2("Dodaj roślinę doniczkową"),
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
                                        description,
                                        new VerticalLayout(
                                                uploadTitle,
                                                uploadHint,
                                                imgUpload
                                        )
                                ),
                                new Button("Zapisz", event -> {
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

                                    plant.setPublished(LocalDate.now(ZoneId.of("Europe/Warsaw")));

                                    if (imgUpload.isUploading()){
                                        return;
                                    }

                                    service.add(plant);
                                    FTPUploader.uploadImage(buffer, plant);
                                    service.update(plant);

                                    UI.getCurrent().navigate(PottedPlantsView.class);
                                    Notification.show("Zapisano...");
                                })
                        )
                )
        );
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long param) {
        PottedPlant plant = service.findById(param);
        polishName.setValue(plant.getPolishName());
        polishFamily.setValue(plant.getPolishFamily());
        latinName.setValue(plant.getLatinName());
        latinFamily.setValue(plant.getLatinFamily());
    }
}
