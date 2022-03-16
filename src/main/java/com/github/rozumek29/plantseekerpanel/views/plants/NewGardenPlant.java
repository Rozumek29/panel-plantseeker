package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.GardenPlant;
import com.github.rozumek29.plantseekerpanel.data.entity.User;
import com.github.rozumek29.plantseekerpanel.data.service.GardenPlantService;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.github.rozumek29.plantseekerpanel.ftp.FTPUploader;
import com.github.rozumek29.plantseekerpanel.security.AuthenticatedUser;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Route(value = "new-garden-plant", layout = MainLayout.class)
@RolesAllowed("user")
public class NewGardenPlant extends HorizontalLayout {

    private final TextField polishName = new TextField("Nazwa Polska");
    private final TextField latinName = new TextField("Nazwa Łacińska");
    private final TextField polishFamily = new TextField("Polska nazwa rodziny");
    private final TextField latinFamily = new TextField("Łacińska nazwa rodziny");
    private final TextField decorativeness = new TextField("Dekoracyjność");
    private final TextField plantUsage = new TextField("Wykorzystanie");
    private final TextArea description = new TextArea("Opis");

    private final TextField sort = new TextField("Pokrój");
    private final TextField height = new TextField("Wysokość");
    private final TextField bark = new TextField("Kora");
    private final TextField shoots = new TextField("Pędy");
    private final TextField leaves = new TextField("Liście");
    private final TextField flowers_desc = new TextField("Opis kwiatów");
    private final TextField flowers_date = new TextField("Okres kwitnienia");
    private final TextField fruits_desc = new TextField("Opis owoców");
    private final TextField fruits_date = new TextField("Okres owocowania");
    private final TextField origin = new TextField("Pochodzenie");

    private final MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();

    private final H4 uploadTitle = new H4("Zdjęcia");
    private final Paragraph uploadHint = new Paragraph("Akceptowane formaty: JPEG, PNG");
    private final Upload imgUpload = new Upload(buffer);

    public NewGardenPlant(GardenPlantService service, AuthenticatedUser authenticatedUser) {

        /*
        Yeah, I know. I should use binder here.
         */

        setSpacing(false);
        Button backbtn = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));
        backbtn.addClickListener(event -> {
            UI.getCurrent().navigate(GardenPlantsView.class);
        });

        imgUpload.setAcceptedFileTypes("image/jpeg", "image/png");
        imgUpload.setMaxFiles(5);

        add(
                new HorizontalLayout(
                        new VerticalLayout(
                                backbtn,
                                new H2("Dodaj roślinę ogrodową"),
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
                                        description,
                                        new VerticalLayout(
                                                uploadTitle,
                                                uploadHint,
                                                imgUpload
                                        )
                                ),
                                new Button("Zapisz", event -> {
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

                                    plant.setPublished(LocalDate.now(ZoneId.of("Europe/Warsaw")));

                                    User user = authenticatedUser.get().get();

                                    plant.setUsername(user.getName());

                                    if (imgUpload.isUploading()){
                                        return;
                                    }


                                    service.add(plant);
                                    FTPUploader.uploadImage(buffer, plant);
                                    service.update(plant);


                                    UI.getCurrent().navigate(GardenPlantsView.class);
                                    Notification.show("Zapisano...");
                                })
                        )
                )

        );
    }

}
