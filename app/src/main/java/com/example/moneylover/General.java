package com.example.moneylover;

import java.text.BreakIterator;

public class General {


    public static long BeginOfDay(long Date){
        return  (Date)-(Date) %(24 * 60 * 60 * 1000);
    }
}
