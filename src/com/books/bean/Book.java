package com.books.bean;

import java.math.BigDecimal;

public class Book {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;

    public Book() {
    }

    public Book(Integer id, String name, BigDecimal price, String author) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return id + "\t\t" + name + "\t\t" + price + "\t\t" + author;
    }
}
