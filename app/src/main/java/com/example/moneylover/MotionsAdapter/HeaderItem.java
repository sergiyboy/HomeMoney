package com.example.moneylover.MotionsAdapter;

import android.support.annotation.NonNull;

import java.util.Date;

public class HeaderItem extends ListItem {

    @NonNull
    private Long date;


    private double ammount;

    public HeaderItem(@NonNull Long date) {
        this.date = date;
    }

    @NonNull
    public Long getDate() {
        return date;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public double getAmmount() {
        return ammount;
    }

    // here getters and setters
    // for title and so on, built
    // using date

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

}