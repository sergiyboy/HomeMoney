package com.example.moneylover;

import android.net.Uri;

import java.io.Serializable;
import java.util.Date;

public class Motion implements Serializable {
    private Long date;
    private String coment;
    private String category;
    private String purse;
    private String title;
    private double amount;
    private String id;

    public Motion(Long date, String coment, String category, String purse, String title, double amount, String id) {
        this.date = date;
        this.coment = coment;
        this.category = category;
        this.purse = purse;
        this.title = title;
        this.amount = amount;
        this.id = id;
    }

    public Motion(String title, Long date) {
        this.date = date;
        this.title = title;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPurse(String purse) {
        this.purse = purse;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComent() {
        return coment;
    }

    public String getCategory() {
        return category;
    }

    public String getPurse() {
        return purse;
    }

    public double getAmount() {
        return amount;
    }

    public String getUri() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getDate() {
        return date;
    }
}