package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.ftp.FTPUploader;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
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
import com.vaadin.flow.router.Route;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Route(value = "new-potted-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class NewPottedPlant extends HorizontalLayout {

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

    public NewPottedPlant(PottedPlantService service) {

        /*
        Yeah, I know. I should use binder here.
         */

        setSpacing(false);

        Button backbtn = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        backbtn.addClickListener(event -> {
            UI.getCurrent().navigate(PottedPlantsView.class);
        });

        imgUpload.setAcceptedFileTypes("image/jpeg", "image/png");
        imgUpload.setMaxFiles(5);

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

                                    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                                    plant.setUsername(userDetails.getUsername());

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
}
