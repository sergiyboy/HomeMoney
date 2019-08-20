package com.example.moneylover.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class CategoryContract {


    public static final String PATH_MOTIONS = "categories";

    public static final Uri BASE_CONTENT_URI =
            Uri.parse(MotionMoneyContract.SCHEMEME +MotionMoneyContract.AUTHORITY);

    private CategoryContract(){

    }
    public static final class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";

        public static final String KEY_ID = BaseColumns._ID;
        public static final String KEY_Type = "type";
        public static final String KEY_name = "name";
        public static final Uri CONTEN_URI =
                Uri.withAppendedPath(BASE_CONTENT_URI,PATH_MOTIONS);

    }

}
