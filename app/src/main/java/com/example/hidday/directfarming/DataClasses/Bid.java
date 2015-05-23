package com.example.hidday.directfarming.DataClasses;

/**
 * Created by hidday on 26/02/2015.
 */
public class Bid {

    private Farmer bidder;
    private int price;

    public Bid(Farmer bidder, int price) {

        this.bidder = bidder;
        this.price = price;

    }

    public Bid(Crop crop) {
        this.price = 9999;
        this.bidder=new Farmer("Empty",0,"","");
    }

    public Farmer getBidder() {
        return this.bidder;
    }

    public void setBidder(Farmer bidder) {
        this.bidder = bidder;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
