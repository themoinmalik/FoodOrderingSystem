package org.onlinefood.restaurant.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "profiles")
public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String openingHours;

    private String website;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonBackReference
    private Restaurant restaurant;


}
