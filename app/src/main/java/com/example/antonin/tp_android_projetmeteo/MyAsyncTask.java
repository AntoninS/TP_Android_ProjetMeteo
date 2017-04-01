package com.example.antonin.tp_android_projetmeteo;

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
import java.util.ArrayList;
import java.util.HashMap;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;

/**
 * Created by Antonin on 01/04/2017.
 */
//http://api.openweathermap.org/data/2.5/forecast?q=villeurbanne,69100&APPID=910f0c05f62e5508a3428198252eed06&units=metric

public class MyAsyncTask extends AsyncTask<Object,Void,Object> {
    ListView lv;
    private Context mContext;

    public MyAsyncTask (Context context){
        mContext = context;
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
        ArrayList contactList = (ArrayList) params[0];
        String url = (String) params[1];
        lv = (ListView) params[2];
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("contacts");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    String gender = c.getString("gender");

                    // Phone node is JSON Object
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");

                    // tmp hash map for single contact
                    HashMap<String, String> contact = new HashMap<>();

                    // adding each child node to HashMap key => value
                    contact.put("id", id);
                    contact.put("name", name);
                    contact.put("email", email);
                    contact.put("mobile", mobile);

                    // adding contact to contact list
                    contactList.add(contact);
                }
                return contactList;
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
        ArrayList contactList = (ArrayList) result;
        ListAdapter adapter = new SimpleAdapter(
                mContext, contactList,
                R.layout.list_item, new String[]{"name", "email",
                "mobile"}, new int[]{R.id.name,
                R.id.email, R.id.mobile});

        lv.setAdapter(adapter);
    }

}
