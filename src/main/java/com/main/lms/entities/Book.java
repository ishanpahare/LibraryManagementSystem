package com.main.lms.entities;

public class Book {
    private String name;
    private String author;
    private String publisher;
    private int isbn;
    private int price;

    public Book(int isbn, String name, int price, String author, String publisher) {
        this.name = name;
        this.isbn = isbn;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
    }

    public Book(int isbn){
        this.isbn=isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
