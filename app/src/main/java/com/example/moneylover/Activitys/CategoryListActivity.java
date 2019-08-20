package com.example.moneylover.Activitys;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.moneylover.CursorAdapters.CategoryCursorAdapter;
import com.example.moneylover.R;
import com.example.moneylover.data.CategoryContract;

public class CategoryListActivity extends Activity
        implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    ListView dataListView;
    Uri currentCategoryUri;

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        dataListView = findViewById(R.id.listViewCategory);
        Toolbar toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }

    public void displayData() {
        //dataListView = findViewById(R.id.listViewCategory);
        //setContentView(R.layout.activity_list_category);

        String[] protection = {
                CategoryContract.CategoryEntry.KEY_ID,
                CategoryContract.CategoryEntry.KEY_name
        };
        Cursor cursor = getContentResolver().query(CategoryContract.CategoryEntry.CONTEN_URI,
                protection, null, null, null);
        while (cursor.moveToNext()) {
            Log.i("cursor -", cursor.getString(cursor.getColumnIndexOrThrow("name")));
        }
        CategoryCursorAdapter cursorAdapter = new CategoryCursorAdapter(this, cursor, false);
        dataListView.setAdapter(cursorAdapter);


        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentCategoryUri = ContentUris.withAppendedId(CategoryContract.CategoryEntry.CONTEN_URI, l);
                String[] projectiom = {"_id", "name"};
                //Cursor cursor = getContentResolver().query(currentCategoryUri,
                //        protection, null, null, null);

                //intent.setData(currentCategoryUri);
                Intent intent = new Intent();
                intent.putExtra("name", "Нов");
                setResult(RESULT_OK, intent);
                finish();
//                onClick(view);
            }
        });
    }

    public void addCategory(View view) {
        Intent intent = new Intent(CategoryListActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String[] projectiom = {"_id", "name"};
        return new CursorLoader(this, currentCategoryUri, projectiom,
                null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            //String retarnName =


        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }


    @Override
    public void onClick(View view) {
    }
}
