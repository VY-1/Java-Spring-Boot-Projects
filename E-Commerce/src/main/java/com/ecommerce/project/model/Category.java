package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Use @Entity to map class object to a database schema table. Can specified entity name using @Entity(name="Some Name")
@Entity(name = "categories")
//Using lombok annotation for setter and getter, constructor and constructor with arg
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    //@Id field is required by the database as a unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    //Using validator to check field is not blank
    @NotBlank
    private String categoryName;


}
