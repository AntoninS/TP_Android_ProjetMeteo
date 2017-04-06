package com.example.antonin.tp_android_projetmeteo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Antonin on 02/04/2017.
 */

public class AdapterMeteo extends ArrayAdapter<Meteo> {
    private Context context;
    private Activity activity;
    private ArrayList<Meteo> previsionsList;
    private static LayoutInflater inflater = null;

    public AdapterMeteo (Context context, Activity activity, int textViewResourceId,ArrayList<Meteo> newPrevisionsList) {
        super( activity, textViewResourceId, newPrevisionsList);
        try {
            this.context = context;
            this.activity = activity;
            this.previsionsList = newPrevisionsList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return previsionsList.size();
    }

    public Meteo getItem(Meteo position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_date;
        public TextView display_temps;
        public TextView display_temperature;
        public ImageView display_IconeTemps;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View lv = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                lv = inflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();

                holder.display_date = (TextView) lv.findViewById(R.id.dt);
                holder.display_temps = (TextView) lv.findViewById(R.id.temps);
                holder.display_temperature = (TextView) lv.findViewById(R.id.temperature);
                holder.display_IconeTemps = (ImageView) lv.findViewById(R.id.iconeTemps);


                lv.setTag(holder);
            } else {
                holder = (ViewHolder) lv.getTag();
            }



            holder.display_date.setText(previsionsList.get(position).getDate());
            holder.display_temps.setText(previsionsList.get(position).getTemps());
            holder.display_temperature.setText(previsionsList.get(position).getTemperature());

            Picasso
                    .with(context)
                    .load(previsionsList.get(position).getIconeTemps())
                    .resize(200,200)
                    .into(holder.display_IconeTemps);

        } catch (Exception e) {


        }
        return lv;
    }
}
