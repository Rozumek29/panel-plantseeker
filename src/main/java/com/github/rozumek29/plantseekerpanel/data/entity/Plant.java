package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plants")
@Getter @Setter
@DiscriminatorColumn(name="plant_type", discriminatorType = DiscriminatorType.STRING)
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String polishName;
    private String latinName;
    private String polishFamily;
    private String latinFamily;
    private String decorativeness;
    private String plantUsage;
    private String description;
    @Column(insertable = false, updatable = false)
    private String plant_type;

    // TODO IMG LIST
}
