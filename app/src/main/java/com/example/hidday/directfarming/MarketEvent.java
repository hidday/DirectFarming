package com.example.hidday.directfarming;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hidday on 25/02/2015.
 */
public class MarketEvent implements Comparable<Date> {

    private Market market;
    private Date date;
    private ArrayList<Crop> cropList;

    public MarketEvent(Market market, Date date) {
        this.market = market;
        this.date = date;
    }

    public Market getMarket() {
        return market;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Crop> getCropList() {
        return cropList;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCropList(ArrayList<Crop> cropList) {
        this.cropList = cropList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketEvent)) return false;

        MarketEvent event = (MarketEvent) o;

        if (date != null ? !date.equals(event.date) : event.date != null) return false;
        if (market != null ? !market.equals(event.market) : event.market != null) return false;

        return true;
    }

    @Override
    public int compareTo(Date anotherDate) {
       try {
           return  ((Date) anotherDate).compareTo(this.date);
       }
       catch (RuntimeException e){
           Log.d("Date error","Erorr: " + e);
           throw e;
       }
    }
}
