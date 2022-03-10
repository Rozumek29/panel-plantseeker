package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("GARDEN")
public class GardenPlant extends Plant{

    private String sort;
    private String height;
    private String bark;
    private String shoots;
    private String leaves;

}
