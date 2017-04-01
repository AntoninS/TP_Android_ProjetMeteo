package com.example.antonin.tp_android_projetmeteo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

// http://api.openweathermap.org/data/2.5/forecast?q=villeurbanne,69100&APPID=910f0c05f62e5508a3428198252eed06&units=metric
// http://api.androidhive.info/contacts/
public class MainActivity extends AppCompatActivity {
    private static String url = "http://api.openweathermap.org/data/2.5/forecast?q=villeurbanne,69100&APPID=910f0c05f62e5508a3428198252eed06&units=metric";
    private ListView lv;


    // URL to get contacts JSON


    ArrayList<HashMap<String, String>> previsionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previsionsList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new MyAsyncTask(this).execute(previsionsList, url, lv);
    }
}
