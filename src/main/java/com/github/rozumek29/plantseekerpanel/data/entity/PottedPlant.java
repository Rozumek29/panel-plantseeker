package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("POTTED")
public class PottedPlant extends Plant{

    private String description;
    private String decorativeness;
    private String plantUsage;
    private String Toxicity;

}
