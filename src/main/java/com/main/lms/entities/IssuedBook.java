package com.main.lms.entities;

import java.time.LocalDate;


public class IssuedBook {
    private int uid;
    private int isbn;
    private int cid;
    private int lid;
    private LocalDate issuedDate;
    private LocalDate returnDate;

    public IssuedBook(int uid,int isbn,int cid,int lid,LocalDate issuedDate,LocalDate returnDate){
        this.uid=uid;
        this.isbn=isbn;
        this.cid=cid;
        this.lid=lid;
        this.issuedDate=issuedDate;
        this.returnDate=returnDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public java.sql.Date getIssuedDate() {
        LocalDate localDate = this.issuedDate;
        java.sql.Date issueDate= java.sql.Date.valueOf(localDate);

        return issueDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public java.sql.Date getReturnDate() {
        LocalDate localDate = this.returnDate;

        java.sql.Date returnDate = java.sql.Date.valueOf(localDate);

        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
