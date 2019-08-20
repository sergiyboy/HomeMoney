package com.example.moneylover.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class PursesContract {

    private PursesContract(){

    }
    public static final String SCHEMEME = "content://";
    public static final String AUTHORITY = "com.example.moneylover";
    public static final String PATH_MOTIONS = "purses";

    public static final Uri BASE_CONTENT_URI =
            Uri.parse(SCHEMEME+AUTHORITY);

    public static final class PursesEntry implements BaseColumns {
        public static final String TABLE_NAME = "purse";

        public static final String KEY_ID = BaseColumns._ID;
        public static final String KEY_name = "name";

        public static final Uri CONTEN_URI =
                Uri.withAppendedPath(BASE_CONTENT_URI,PATH_MOTIONS);
    }
}
