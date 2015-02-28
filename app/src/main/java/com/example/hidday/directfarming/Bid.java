package com.example.hidday.directfarming;

import android.support.v4.util.SimpleArrayMap;
import android.util.ArrayMap;

import com.example.hidday.directfarming.Crop;

import java.util.ArrayList;

/**
 * Created by hidday on 26/02/2015.
 */
public class Bid {

    private Crop crop;
    private String winner;
    private int price;
    private SimpleArrayMap<String,Integer> biddingHistory;

    public Bid(Crop crop, String winner, int price) {
        this.biddingHistory=new SimpleArrayMap<>();

        this.crop = crop;
        this.winner = winner;
        this.price = price;

        this.biddingHistory.put(winner,price);
    }

    public Bid(Crop crop) {
        this.biddingHistory=new SimpleArrayMap<>();

        this.crop = crop;
        this.price = 9999;
        this.winner= "Empty";
    }

    public boolean placeBid(String bidder, int bidPrice)
    {
        if(bidPrice<this.price)
        {
            this.winner=bidder;
            this.price=bidPrice;
            this.biddingHistory.put(bidder,bidPrice);
            return true;
        }
        else
        {
            this.biddingHistory.put(bidder,bidPrice);
            return false;
        }
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public SimpleArrayMap<String, Integer> getBiddingHistory() {

        return biddingHistory;
    }

    public void setBiddingHistory(SimpleArrayMap<String, Integer> biddingHistory) {
        this.biddingHistory = biddingHistory;
    }
}
