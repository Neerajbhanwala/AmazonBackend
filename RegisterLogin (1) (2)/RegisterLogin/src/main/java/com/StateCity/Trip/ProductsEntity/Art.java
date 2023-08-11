package com.StateCity.Trip.ProductsEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Art
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String image;

    private String title;

    private String description;

    private String color;

    private String brand;

    private String offer;

    private String stock;

    private String category;

    private float rating;

    private int originalPrice;

    private int salePrice;

}