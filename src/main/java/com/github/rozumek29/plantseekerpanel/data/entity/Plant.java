package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "plants")
@Getter @Setter
@DiscriminatorColumn(name="product_type", discriminatorType = DiscriminatorType.STRING)
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String polishName;
    private String latinName;
    private String polishFamily;
    private String latinFamily;

    private String LightConditions;
    private String subsoil;
    private String watering;

}
