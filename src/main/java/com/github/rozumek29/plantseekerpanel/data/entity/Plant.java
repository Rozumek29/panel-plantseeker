package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private LocalDate published;
    private String username;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "plant_id")
    private List<PlantImage> images;
}
