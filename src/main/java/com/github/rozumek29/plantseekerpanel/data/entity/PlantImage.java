package com.github.rozumek29.plantseekerpanel.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "plant_images")
@Getter @Setter @NoArgsConstructor
public class PlantImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img_name;

    public PlantImage(String img_name){
        this.img_name = img_name;
    }

    @Override
    public String toString() {
        return this.img_name;
    }
}
