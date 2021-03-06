package com.example.hidday.directfarming.DataClasses;

import android.util.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by hidday on 25/02/2015.
 */
public class MarketEvent implements Comparable<GregorianCalendar> {

    private Market market;
    private GregorianCalendar date;
    private ArrayList<Bid> bidList;
    private int ID;

    public MarketEvent(Market market, GregorianCalendar date, int id) {
        this.bidList=new ArrayList<>();
        this.market = market;
        this.date = date;
        this.ID=id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(ArrayList<Bid> bidList) {
        this.bidList = bidList;
    }

    public Market getMarket() {
        return market;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
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
    public int compareTo(GregorianCalendar anotherDate) {
       try {
           return  ((GregorianCalendar) anotherDate).compareTo(this.date);
       }
       catch (RuntimeException e){
           Log.d("Date error","Erorr: " + e);
           throw e;
       }
    }
}
