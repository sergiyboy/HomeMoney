package com.example.moneylover.Activitys;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneylover.General;
import com.example.moneylover.Motion;
import com.example.moneylover.R;
import com.example.moneylover.data.MotionMoneyContract.MotionEntry;

import java.util.Calendar;

public class MotionActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView categoryName;
    private EditText purseName;
    private EditText comentName;
    private EditText amountName;
    private Double amountDouble;
    private int purseID;
    long dateInt;
    TextView currentData;
    Calendar data = Calendar.getInstance();

    Motion motionObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);
        categoryName = findViewById(R.id.categoryName);
        currentData  = findViewById(R.id.dateText);
        purseName = findViewById(R.id.purseName);
        comentName = findViewById(R.id.comentName);


        motionObject=(Motion) getIntent().getSerializableExtra("motionObject");
        if (motionObject!=null) {

            categoryName.setText(motionObject.getCategory());
            dateInt=motionObject.getDate();
            purseName.setText(motionObject.getPurse());
            comentName.setText(motionObject.getComent());
        }
        setInitialDateTime();

    }
    // отображаем диалоговое окно для выбора даты
    public void setData(View v) {
        new DatePickerDialog(MotionActivity.this, d,
                data.get(Calendar.YEAR),
                data.get(Calendar.MONTH),
                data.get(Calendar.DAY_OF_MONTH))
                .show();
    }
    // установка начальных даты и времени
    private void setInitialDateTime() {

//        dateInt=(long)data.getTimeInMillis();
        //Long dateNule=0L;
        if (dateInt==0L){
            dateInt= General.BeginOfDay((long)data.getTimeInMillis());
        };

        currentData.setText(DateUtils.formatDateTime(this,
                dateInt,
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            data.set(Calendar.YEAR, year);
            data.set(Calendar.MONTH, monthOfYear);
            data.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateInt=data.getTimeInMillis();
            setInitialDateTime();
        }
    };


    public void saveMotion(View view) {
        categoryName=findViewById(R.id.categoryName);
        purseName=findViewById(R.id.purseName);
        comentName=findViewById(R.id.comentName);
        amountName=findViewById(R.id.amountName);
        if (amountName==null){
            amountDouble = 0.0;
        }
        else {
            amountDouble = Double.parseDouble(amountName.getText().toString().trim());
        }
        insertMotion();
    }
    private void insertMotion(){
        String category = categoryName.getText().toString().trim();
        String comentar = comentName.getText().toString().trim();
        String purse = purseName.getText().toString().trim();
        String amount = amountName.getText().toString().trim();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MotionEntry.KEY_Category,category);
        contentValues.put(MotionEntry.KEY_Purse,purse);
        contentValues.put(MotionEntry.KEY_amount,amountDouble);
        contentValues.put(MotionEntry.KEY_date,dateInt);
        contentValues.put(MotionEntry.KEY_coment,comentar);

        ContentResolver contentResolver = getContentResolver();
        Uri uri =contentResolver.insert(MotionEntry.CONTEN_URI,contentValues);
        if (uri==null){

            Toast.makeText(this, "Data do not save", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data save", Toast.LENGTH_SHORT).show();
        }
    }

    public void setPurse(View view) {
        Intent motionIntent =new Intent(MotionActivity.this, MotionActivity.class);
        startActivity(motionIntent);

    }

    public void setCategory(View view) {
        onClick(view);
//        Intent categoryIntent =new Intent(MotionActivity.this, CategoryListActivity.class);
//        startActivityForResult(categoryIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {return;}
        String returnCategory = data.getStringExtra("name");
        categoryName=findViewById(R.id.categoryName);
        categoryName.setText("" + returnCategory);    }

    @Override
    public void onClick(View view) {
        Intent categoryIntent =new Intent(MotionActivity.this, CategoryListActivity.class);
        startActivityForResult(categoryIntent,1);
    }
}