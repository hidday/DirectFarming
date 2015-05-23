package com.example.hidday.directfarming.DataClasses;

import android.support.v4.util.SimpleArrayMap;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hidday on 13/05/2015.
 */
public class MarketStand {

    private Crop crop;
    private Farmer currentWinner;
    private Bid winningBid;
    private SimpleArrayMap<Date,Bid> bidsLog;
    private MarketEvent marketEvent;



    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public MarketStand(Crop crop, Farmer firstBidder,Bid firstBid, MarketEvent marketEvent) {
        this.crop = crop;
        this.currentWinner = firstBidder;
        this.winningBid=firstBid;
        this.marketEvent = marketEvent;
        this.bidsLog=new SimpleArrayMap<Date,Bid>();

        Calendar date = Calendar.getInstance();
        this.bidsLog.put(date.getTime(),firstBid);
    }

    public MarketStand(Crop crop, MarketEvent marketEvent) {
        this.crop = crop;
        this.marketEvent = marketEvent;
        this.bidsLog=new SimpleArrayMap<Date,Bid>();
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Farmer getCurrentWinner() {
        return currentWinner;
    }

    public void setCurrentWinner(Farmer currentWinner) {
        this.currentWinner = currentWinner;
    }

    public SimpleArrayMap<Date, Bid> getBidsLog() {
        return bidsLog;
    }

    public void setBidsLog(SimpleArrayMap<Date, Bid> bidsLog) {
        this.bidsLog = bidsLog;
    }

    public MarketEvent getMarketEvent() {
        return marketEvent;
    }

    public void setMarketEvent(MarketEvent marketEvent) {
        this.marketEvent = marketEvent;
    }
}
