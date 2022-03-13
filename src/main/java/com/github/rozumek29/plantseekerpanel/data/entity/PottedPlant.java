package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@DiscriminatorValue("POTTED")
public class PottedPlant extends Plant{

    private String toxicity;
    private String lightConditions;
    private String subsoil;
    private String watering;

}
