package edu.school21.models;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Integer price;

    public Product() {}

    public Product(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Product p = (Product) obj;
        return Objects.equals(this.id, p.id) && Objects.equals(this.name, p.name)
                && Objects.equals(this.price, p.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product : {" +
                "\nid = " + this.id +
                "\nname = " + this.name +
                "\nprice = " + this.price +
                "\n}";
    }
}
