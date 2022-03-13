package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter
@DiscriminatorValue("GARDEN")
public class GardenPlant extends Plant{

    private String sort;
    private String height;
    private String bark;
    private String shoots;
    private String leaves;
    private String flowers_desc;
    private LocalDate flowers_date;
    private String fruits_desc;
    private LocalDate fruits_date;
    private String origin;

}
