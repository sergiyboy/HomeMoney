package com.example.moneylover.MotionsAdapter;

public abstract class ListItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_MOTION = 1;

    abstract public int getType();
}
