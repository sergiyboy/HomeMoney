package com.example.moneylover.MotionsAdapter;

import android.support.annotation.NonNull;

import com.example.moneylover.Motion;

public class MotionItem extends ListItem{
    @NonNull
    private Motion motion;

    public MotionItem(@NonNull Motion motion) {
        this.motion = motion;
    }

    @NonNull
    public Motion getmotion() {
        return motion;
    }

    // here getters and setters
    // for title and so on, built
    // using event

    @Override
    public int getType() {
        return TYPE_MOTION;
    }

}
