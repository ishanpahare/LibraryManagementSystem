package com.main.lms.entities;

public class Vendor {
    private int vid;
    private String name;
    private int isbn;
    private String bookName;
    private int price;
    private String author;
    private String publisher;

    public Vendor(int vid,String name,int isbn,String bookName,int price,String author,String publisher){
        this.vid=vid;
        this.name=name;
        this.isbn=isbn;
        this.bookName=bookName;
        this.price=price;
        this.author=author;
        this.publisher=publisher;
    }

    public Vendor(int vid){
        this.vid=vid;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
