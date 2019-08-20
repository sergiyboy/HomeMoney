package com.example.moneylover.Activitys;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneylover.R;
import com.example.moneylover.data.CategoryContract;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    EditText categoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                categoryName=findViewById(R.id.editCategory);
            insertMotion();
        }
        private void insertMotion(){
            String category = categoryName.getText().toString().trim();

            ContentValues contentValues = new ContentValues();
            contentValues.put(CategoryContract.CategoryEntry.KEY_name,category);

            ContentResolver contentResolver = getContentResolver();
            Uri uri =contentResolver.insert(CategoryContract.CategoryEntry.CONTEN_URI,contentValues);
        }

    });
    }

    @Override
    public void onClick(View view) {

    }
}
