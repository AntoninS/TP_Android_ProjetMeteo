package com.example.antonin.tp_android_projetmeteo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Antonin on 01/04/2017.
 */

public class Meteo {
    public String date;
    public String temps;
    public Double temperature;
    public String iconeTemps;


    public Meteo(){
        this.date = null;
        this.temps = null;
        this.temperature = null;
        this.iconeTemps = null;
    }

    public Meteo(String newDate, String newTemps, Double newTemperature, String newIconeTemps)
    {
        this.date = newDate;
        this.temps = newTemps;
        this.temperature = newTemperature;
        this.iconeTemps = newIconeTemps;
    }


    public String getDateBonFormat(Long dateFormatTimestamp) {
        String timestamp = dateFormatTimestamp.toString();
        DateFormat sf = new SimpleDateFormat("dd/MM/yyyy 'à' HH'h'");
        Date date = new Date(Long.parseLong(timestamp)*1000);
        String dateBonFormat = sf.format(date);
        return dateBonFormat;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(Long date) {
        this.date = getDateBonFormat(date);
    }

    public String getTemps() { return this.temps; }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getIconeTemps() { return this.iconeTemps; }

    public void setIconeTemps(int idIconeTemps) {
        String iconeTemporaire = "";

        if( idIconeTemps == 800){
            iconeTemporaire = "http://openweathermap.org/img/w/01d.png";
        } else {
            int id = idIconeTemps / 100;
            switch(id) {
                case 2 : iconeTemporaire = "http://openweathermap.org/img/w/11d.png";
                    break;
                case 3 : iconeTemporaire = "http://openweathermap.org/img/w/09d.png";
                    break;
                case 5 : iconeTemporaire = "http://openweathermap.org/img/w/10d.png";
                    break;
                case 6 : iconeTemporaire = "http://openweathermap.org/img/w/13d.png";
                    break;
                case 7 : iconeTemporaire = "http://openweathermap.org/img/w/50d.png";
                    break;
                case 8 : iconeTemporaire = "http://openweathermap.org/img/w/02d.png";
                    break;

            }
        }
        this.iconeTemps = iconeTemporaire;
    }

    public String getTemperature() {

        return temperature.toString() + "°C";
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

}