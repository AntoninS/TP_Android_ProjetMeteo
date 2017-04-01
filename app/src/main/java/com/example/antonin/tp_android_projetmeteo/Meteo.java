package com.example.antonin.tp_android_projetmeteo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Antonin on 01/04/2017.
 */

public class Meteo {
    private Long date;
    private String humidite;
    private String pression;
    private Double temperature;


    public Meteo(){
        this.date = null;
        this.humidite = null;
        this.pression = null;
        this.temperature = null;
    }

    public Meteo(Long newDate, String newHumidite, String newPression, Double newTemperature)
    {
        this.date = newDate;
        this.humidite = newHumidite;
        this.pression = newPression;
        this.temperature = newTemperature;
    }


    public String getUpdateTimeToString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String updatedOn = df.format(new Date(this.date*1000));

        //add 0 at the beginning if day < 10
        if(updatedOn.charAt(1) == ' '){
            updatedOn = "0" + updatedOn;
        }
        return updatedOn;
    }

    public Long getDate(){
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    /*
    public String getIcone() {
        return icone;
    }

    public void setIcone(int actualId, Activity iconAsset) {
        this.setWeatherIcon(actualId, iconAsset);
    }
    */

    public String getHumidite() {
        return humidite;
    }

    public void setHumidite(String humidite) {
        this.humidite = humidite;
    }

    public String getPression() {
        return pression;
    }

    public void setPression(String pression) {
        this.pression = pression;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

}