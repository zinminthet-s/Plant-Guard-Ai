package com.zinminthet.plantguardai.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "advertisements")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    private Long id;

    @Column(name = "image_path")
    private String imagePath;

}
