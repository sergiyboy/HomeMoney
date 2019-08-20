package com.example.moneylover;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.example.moneylover.Activitys.MotionActivity;
import com.example.moneylover.MotionsAdapter.HeaderItem;
import com.example.moneylover.MotionsAdapter.ListItem;
import com.example.moneylover.MotionsAdapter.MotionItem;
import com.example.moneylover.MotionsAdapter.MotionsAdapter;
import com.example.moneylover.data.MoneyLoverContenProvider;
import com.example.moneylover.data.MotionMoneyContract;
import com.example.moneylover.data.MotionMoneyContract.MotionEntry;
import com.example.moneylover.data.dataDBOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private List<ListItem> items = new ArrayList<>();
    ListView dataListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        Uri currentMotion = intent.getData();
        if (currentMotion==null) {

        }
        setContentView(R.layout.activity_main);
        //dataListView = findViewById(R.id.listView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        SQLiteDatabase db = SQLiteDatabase.create()
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addNewMotion(View view) {
        Intent motionIntent =new Intent(MainActivity.this, MotionActivity.class);
        startActivity(motionIntent);
    }

    public void displayData() {
        setContentView(R.layout.activity_main);
        items.clear();
        Map<Long, List<Motion>> events = toMap(loadEvents());

        String[] protection = {
                MotionEntry.KEY_ID,
                MotionEntry.KEY_Purse,
                MotionEntry.KEY_date,
                MotionEntry.KEY_Category,
                MotionEntry.KEY_coment,
                MotionEntry.KEY_amount
        };
        String textFilter = MotionEntry.KEY_date + "=?";
        for (Long date : events.keySet()) {
            HeaderItem header = new HeaderItem(date);
            items.add(header);
            double tAmmount=0;
            for (Motion motion : events.get(date)) {
                    String[] filter = new String[]{Long.toString(date)};//query.date;
                    Cursor cursor = getContentResolver().query(MotionEntry.CONTEN_URI,
                            protection, textFilter, filter, null);

                    while (cursor.moveToNext()) {
                        String purse = cursor.getString(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_Purse));
                        String category = cursor.getString(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_Category));
                        String coment = cursor.getString(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_coment));
                        double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_amount));
                        Long dateM = cursor.getLong(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_date));
                        String id = cursor.getString(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_ID));
                        tAmmount=tAmmount+amount;
                        Motion motionObject = new Motion(dateM, coment, category, purse, "1", amount, id);
                        MotionItem item = new MotionItem(motionObject);
                        items.add(item);
                    }
                }
            header.setAmmount(tAmmount);
            }
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MotionsAdapter(items));


//            recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Log.i("Id now",""+l);
//                    Intent intent = new Intent(MainActivity.this, MotionActivity.class);
//                    Uri currentMotionUri = ContentUris.withAppendedId(MotionEntry.CONTEN_URI, l);
//                    intent.setData(currentMotionUri);
//                    startActivity(intent);
//
//                }
//            });
        }

    @NonNull
    private Map<Long, List<Motion>> toMap(@NonNull List<Motion> events) {
        Map<Long, List<Motion>> map = new TreeMap<>();
        for (Motion event : events) {
            List<Motion> value = map.get(event.getDate());
            if (value == null) {
                value = new ArrayList<>();
                map.put(event.getDate(), value);
            }
            value.add(event);
        }
        return map;
    }
    private List<Motion> loadEvents() {
        List<Motion> motions = new ArrayList<>();
        dataDBOpenHelper dataDBHelper=MoneyLoverContenProvider.dbOpenHelper;// db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        SQLiteDatabase db = dataDBHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT "+MotionEntry.KEY_date+" FROM "+MotionEntry.TABLE_NAME+" GROUP BY "+MotionEntry.KEY_date+" ORDER BY "+MotionEntry.KEY_date+";", null);
        String textFilter=MotionEntry.KEY_date+"=?";
        int i=0;
        if(query.moveToFirst()){
            do{
                i++;
                motions.add(new Motion("motion"+i,query.getLong(0)));
            }
            while(query.moveToNext());  //          int1++;
        }
        query.close();
        db.close();

        return motions;
    }

}