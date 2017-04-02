package com.example.antonin.tp_android_projetmeteo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;

/**
 * Created by Antonin on 01/04/2017.
 */

public class MyAsyncTask extends AsyncTask<Object,Void,Object> {
    ListView lv;
    private Context mContext;
    public MainActivity activity;

    public MyAsyncTask (Context context, MainActivity a){
        mContext = context;
        this.activity = a;
    }



    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected Object doInBackground(Object... params) {
        HttpHandler sh = new HttpHandler();
        ArrayList previsionsList = (ArrayList) params[0];
        String url = (String) params[1];
        lv = (ListView) params[2];
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONObject details, main, sys, oneValueListJSON;

                // Getting JSON Array node
                JSONArray previsions = jsonObj.getJSONArray("list");

                // looping through All Contacts
                for (int i = 0; i < previsions.length(); i++) {

                    JSONObject p = previsions.getJSONObject(i);
                    details = p.getJSONArray("weather").getJSONObject(0);
                    main = p.getJSONObject("main");
                    sys = p.getJSONObject("sys");

                    //new object for new forecast
                    Meteo donneesMeteo = new Meteo();

                    donneesMeteo.setDate(p.getLong("dt"));
                    donneesMeteo.setTemps(details.getString("main"));
                    donneesMeteo.setHumidite(main.getString("humidity"));
                    donneesMeteo.setPression(main.getString("pressure"));
                    donneesMeteo.setTemperature(main.getDouble("temp"));
                    //donneesMeteo.setIcone(details.getInt("id"),weatherFragmentActivity);





                    /*
                    String timestamp = p.getString("dt");
                    DateFormat sf = new SimpleDateFormat("dd/MM/yyyy 'à' HH");
                    Date date = new Date(Long.parseLong(timestamp)*1000);

                    String dt = sf.format(date);
                    */

                    // adding contact to contact list
                    previsionsList.add(donneesMeteo);
                }
                return previsionsList;
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
                */
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
            /*
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
            */

        }

        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
        ArrayList previsionsList = (ArrayList) result;

        /*
        ListAdapter adapter = new SimpleAdapter(
                mContext, previsionsList,
                R.layout.list_item, new String[]{"dt", "email", "mobile"},
                new int[]{R.id.dt, R.id.email, R.id.mobile});

        lv.setAdapter(adapter);
        */

        AdapterMeteo adapter = new AdapterMeteo (activity, 0, previsionsList);
        lv.setAdapter(adapter);
    }

}
