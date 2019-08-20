package com.example.moneylover.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.moneylover.data.MotionMoneyContract.MotionEntry;
import com.example.moneylover.data.PursesContract.PursesEntry;


public class dataDBOpenHelper extends SQLiteOpenHelper {
    public dataDBOpenHelper( Context context) {
        super(context, MotionMoneyContract.DATABASE_NAME, null, MotionMoneyContract.DATABASE_VERSION);
//        Log.i("yesT","not very good");
//        onCreate(SQLiteDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOTION_TABLE = "CREATE TABLE "+MotionEntry.TABLE_NAME + "("
                + MotionEntry.KEY_ID+" INTEGER PRIMARY KEY,"
                +MotionEntry.KEY_date+" LONG,"
                +MotionEntry.KEY_coment+" TEXT,"
                +MotionEntry.KEY_Category+" TEXT,"
                + MotionEntry.KEY_Purse+" TEXT,"
                +MotionEntry.KEY_amount+" DOUBLE "+")";
            db.execSQL(CREATE_MOTION_TABLE);
          //  Log.i("yesT","very good");

        String CREATE_PURSES_TABLE = "CREATE TABLE "+ PursesEntry.TABLE_NAME + "("
                + PursesEntry.KEY_ID+" INTEGER PRIMARY KEY,"
                +PursesEntry.KEY_name+" TEXT )";
        db.execSQL(CREATE_PURSES_TABLE);

        String CREATE_CATEGORY_TABLE = "CREATE TABLE "+ CategoryContract.CategoryEntry.TABLE_NAME + "("
                + CategoryContract.CategoryEntry.KEY_ID+" INTEGER PRIMARY KEY,"
                + CategoryContract.CategoryEntry.KEY_name+" TEXT,"
                + CategoryContract.CategoryEntry.KEY_Type+" TEXT )";
        db.execSQL(CREATE_CATEGORY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ MotionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ PursesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ CategoryContract.CategoryEntry.TABLE_NAME);
        onCreate(db);
    }
}
