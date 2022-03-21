package com.github.rozumek29.plantseekerpanel.views.plants;

import com.github.rozumek29.plantseekerpanel.data.entity.PottedPlant;
import com.github.rozumek29.plantseekerpanel.data.service.PottedPlantService;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class EditPottedPlant{

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

    @Autowired
    private PottedPlantService service;

    public VerticalLayout createDialogLayout(Dialog dialog, Long plantID){
        dialog.getElement().setAttribute("aria-label", "Edytuj rośinę");

        PottedPlant plant = service.findById(plantID);

        H2 headLine = new H2("Edytuj roślinę");

        VerticalLayout verticalLayout = new VerticalLayout(
                headLine
        );

        return verticalLayout;
    }
}
