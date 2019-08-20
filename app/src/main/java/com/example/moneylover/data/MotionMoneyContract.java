package com.example.moneylover.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class MotionMoneyContract implements BaseColumns {

    private MotionMoneyContract(){

    }
    public static final int DATABASE_VERSION=3;
    public static final String DATABASE_NAME = "motions";

    public static final String SCHEMEME = "content://";
    public static final String AUTHORITY = "com.example.moneylover";
    public static final String PATH_MOTIONS = "motions";

    public static final Uri BASE_CONTENT_URI =
            Uri.parse(SCHEMEME+AUTHORITY);

    public static final class MotionEntry{
        public static final String TABLE_NAME = "motions";

        public static final String KEY_ID = BaseColumns._ID;
        public static final String KEY_date = "date";
        public static final String KEY_coment = "coment";
//        public static final CategoryContract KEY_Category =
        public static final String KEY_Category = "category";
        public static final String KEY_Purse = "purse";
        public static final String KEY_amount = "amount";

        public static final Uri CONTEN_URI =
                Uri.withAppendedPath(BASE_CONTENT_URI,PATH_MOTIONS);
    }
}
