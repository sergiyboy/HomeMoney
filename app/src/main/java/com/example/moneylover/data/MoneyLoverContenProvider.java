package com.example.moneylover.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MoneyLoverContenProvider extends ContentProvider {


    public static dataDBOpenHelper dbOpenHelper;
    private static final int MOTIONS=111;
    private static final int MOTION1=222;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(MotionMoneyContract.AUTHORITY, MotionMoneyContract.PATH_MOTIONS, MOTIONS);
        uriMatcher.addURI(MotionMoneyContract.AUTHORITY, MotionMoneyContract.PATH_MOTIONS+"/#", MOTION1);
        uriMatcher.addURI(MotionMoneyContract.AUTHORITY, CategoryContract.PATH_MOTIONS, MOTIONS);
        uriMatcher.addURI(MotionMoneyContract.AUTHORITY, CategoryContract.PATH_MOTIONS+"/#", MOTION1);
    }


    @Override
    public boolean onCreate() {
        dbOpenHelper = new dataDBOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs ,  String sortOrder) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor;
        String tableName=getTableNameFromUri(uri);
        int match = uriMatcher.match(uri);
        switch (match){
            case MOTIONS:
                cursor=db.query(tableName,projection,selection,
                selectionArgs,null,null,sortOrder);
                break;
            case MOTION1:
                selection= MotionMoneyContract.MotionEntry.KEY_ID+"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor=db.query(tableName,projection,selection,
                        selectionArgs,null,null,sortOrder);
                break;

                default:
                    Log.e("Error","Can not query uri");
                    throw new IllegalArgumentException("Can not query uri ");

        }
        return cursor;
    }

    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        String tableName=getTableNameFromUri(uri);

        switch (match){
            case MOTIONS:
                long id = db.insert(tableName,null,values);

                if (id==-1){
                    Log.e("Error","Can not query uri" + uri);
                    return null;
                }
                return ContentUris.withAppendedId(uri,id);
            default:
                Log.e("Error","Can not query uri");
                throw new IllegalArgumentException("Can not query uri");
        }

    }

    @Override
    public int delete( Uri uri,  String s,  String[] strings) {
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        String tableName=getTableNameFromUri(uri);
        int match = uriMatcher.match(uri);
        switch (match){
            case MOTIONS:
                return db.update(tableName,values,selection,selectionArgs);
            case MOTION1:
                selection= MotionMoneyContract.MotionEntry.KEY_ID+"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return db.update(tableName,values,selection,selectionArgs);

            default:
                Log.e("Error","Can not query uri");
                throw new IllegalArgumentException("Can not query uri ");

        }
    }

    private String getTableNameFromUri(Uri uri){
        //switch с константами не захотел работать((
        if (uri.equals(MotionMoneyContract.MotionEntry.CONTEN_URI)){
            return MotionMoneyContract.MotionEntry.TABLE_NAME;
        }
        else if (uri.equals(CategoryContract.CategoryEntry.CONTEN_URI)){
            return CategoryContract.CategoryEntry.TABLE_NAME;
        }
        else return "";
    }
}
//content://com.android.uraal.MoneyLover/