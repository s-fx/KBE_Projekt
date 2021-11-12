package com.wir.kbe_project.application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;


@Entity
public class Laptop {

    /**
     * TODO: Change Long id to UUID id
     */
    
    private @Id @GeneratedValue UUID id;

    @NotNull(message = "Brand name must be betweenn 3 and 20 characters")
    @Size(min = 3, max = 20)
    private String brand;

    @NotNull(message = "Price can not be 0")
    private double price;

    public Laptop() {}

    public Laptop(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(laptop.price, price) == 0 && Objects.equals(id, laptop.id) && Objects.equals(brand, laptop.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, price);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
