package com.example.antonin.tp_android_projetmeteo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// http://api.openweathermap.org/data/2.5/forecast?q=villeurbanne,69100&APPID=910f0c05f62e5508a3428198252eed06&units=metric
// http://api.androidhive.info/contacts/

public class MainActivity extends AppCompatActivity {

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private static String url = "http://api.openweathermap.org/data/2.5/forecast?q=villeurbanne,69100&APPID=910f0c05f62e5508a3428198252eed06&units=metric";
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Meteo> previsionsList = new ArrayList<Meteo>();

        lv = (ListView) findViewById(R.id.list);

        if (isNetworkConnected()){
            new MyAsyncTask(this, this).execute(previsionsList, url, lv);
        }
        else {
            Toast.makeText(this, "Vous n'êtes pas connecté à internet !", Toast.LENGTH_LONG).show();
        }
    }
}
