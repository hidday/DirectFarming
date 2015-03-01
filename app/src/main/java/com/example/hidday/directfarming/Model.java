package com.example.hidday.directfarming;

import android.content.Context;
import android.util.Log;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by hidday on 28/02/2015.
 */
public class Model {

    // class member
    private static Model instance;

    // private constructor
    private Model(Context context){
        init(context);
    }
    //public accessor
    public static Model getInstance(Context context){
        if (instance == null) {
            instance = new Model(context);
        }
        return instance;
    }

    private void init(Context context){
        Log.d("Model", "Initializing DB ");
        //Parse.initialize(context, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        Parse.initialize(context, "zlDQOcXVpC2a3exWmuvGl3FcWGvsmpn0MgFC7i1D", "I29kas1FRUnzesQxtqegGlpBEoHUDMhmft5QtCDY");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

    }

    public void addMarket(Market market){
        Log.d("DB","Model addMarket "+market);
        ParseObject newMarket=marketToJson(market);
        //		newStudent.saveInBackground();
        try {
            newMarket.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /////////////No Background Operation//////////////////

    public ArrayList<Market> getAllMarkets(){
        Log.d("HA", "Model - Getting all markets");
        ArrayList<Market> markets = new ArrayList<Market>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Markets");
        try{
            List<ParseObject> objects=query.find() ;
            if(objects!=null){
                Log.d("HA", "Model - Getting all markets - done (), objects.size()=" +objects.size() );

                for(ParseObject o: objects){
                    markets.add(jsonToMarket(o));
                }
                Log.d("HA", "Model - after coversion markets.size()=" +markets.size());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
            Log.e("HA", "Model - query.find() exeption"+e.toString());
        }
        Log.d("HA", "Model - Getting all markets finished" );

        return markets;
    }


    ////////////Helper method to covert from ParseObject to Student //////////////
    public Market jsonToMarket(ParseObject p){
        Market market= new Market(p.getString("Name"),p.getString("Phone"),p.getString("Address"),p.getString("Manager"));
        Log.d("HA", "Model - jsonToMarket" +market );
        return market;
    }

    ////////////Helper method to covert from Student to ParseObject /////////////
    public ParseObject marketToJson(Market market){
        ParseObject po = new ParseObject("Market");
        po.put("Name",market.getName());
        po.put("Phone", market.getPhone());
        po.put("Address", market.getAddress());
        po.put("Manager", market.getManager());
        return po;
    }


    public void editMarket(Market s) {
        final Market s1=s;
        ParseObject marketToEdit=null;
        Log.d("HA", "Model.editStudent index= " + s1.getName());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markets");
        query.whereEqualTo("Name", s.getName());
        try{
            List<ParseObject> marketList=query.find();
            if (marketList.size()>0) {
                Log.d("HA", "Model.editStudent Retrieved " + marketList.size() + " students");
                marketToEdit=marketList.get(0);
                marketToEdit.put("Name",s1.getName());
                marketToEdit.put("Phone",s1.getPhone());
                marketToEdit.put("Address",s1.getAddress());
                marketToEdit.put("Manager",s1.getManager());

            }
        }
        catch(ParseException e){
            e.printStackTrace();
        }


        marketToEdit.saveInBackground();

    }

     //Retrieve the object by id

    public Market getMarketByName(String Name){
        Market noMarket=new Market("not found","","","");
        ArrayList<Market> allMarkets= getAllMarkets();

        for (int i = 0; i <allMarkets.size() ; i++) {
            if (allMarkets.get(i).getName().equals(Name)) {
                return allMarkets.get(i);
            }
        }

        return noMarket;
    }



    public void deleteMarket(String Name){
        Log.d("HA", "Model.deleteMarket index= " +Name);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markets");
        query.whereEqualTo("index", Name);
        try{
            List<ParseObject> marketList=query.find();
            if (marketList.size()>0) {
                Log.d("HA", "Model.deleteMarket Retrieved " + marketList.size() + " market");
                marketList.get(0).deleteInBackground();

            }
        }
        catch (ParseException e1) {
            e1.printStackTrace();
            Log.e("HA", "Model.deleteMarket Error: " + e1.getMessage());
        }


    }


    /*****************************************************/
    //Marketevents retreving methods
    /*****************************************************/


   public void addMarketEvent(MarketEvent marketEvent) {
        Log.d("DB", "Model addMarketEvent " + marketEvent);
        ParseObject newMarketEvent = marketEventToJson(marketEvent);
        //		newStudent.saveInBackground();
        try {
            newMarketEvent.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /////////////No Background Operation//////////////////

    public ArrayList<MarketEvent> getAllMarketEvents() {
        Log.d("HA", "Model - Getting all marketEvents");
        ArrayList<MarketEvent> marketEvents = new ArrayList<MarketEvent>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("MarketEvents");
        try {
            List<ParseObject> objects = query.find();
            if (objects != null) {
                Log.d("HA", "Model - Getting all marketEvents - done (), objects.size()=" + objects.size());

                for (ParseObject o : objects) {
                    marketEvents.add(jsonToMarketEvent(o));
                }
                Log.d("HA", "Model - after coversion marketEvents.size()=" + marketEvents.size());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("HA", "Model - query.find() exeption" + e.toString());
        }
        Log.d("HA", "Model - Getting all marketEvents finished");

        return marketEvents;
    }


    ////////////Helper method to covert from ParseObject to Student //////////////
    public MarketEvent jsonToMarketEvent(ParseObject p) {
        Market market= getMarketByName(p.getString("Name"));

        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(p.getDate("Date"));
        MarketEvent marketEvent= new MarketEvent(market,calendar ,p.getInt("ID"));
        Log.d("HA", "Model - jsonToMarketEvent" + marketEvent);
        return marketEvent;
    }

    ////////////Helper method to covert from MarketEvent to ParseObject /////////////
    public ParseObject marketEventToJson(MarketEvent marketEvent) {
        ParseObject po = new ParseObject("MarketEvents");
        po.put("ID", marketEvent.getID());
        po.put("Name", marketEvent.getMarket().getName());
        po.put("Date", marketEvent.getDate());
        return po;
    }


    //Retrieve the object by id

    public MarketEvent getMarketEventByID(int id) {
        MarketEvent noMarketEvent = new MarketEvent(new Market("not found", "", "", ""),new GregorianCalendar(2004,4,4),999);
        ArrayList<MarketEvent> allMarketEvents = getAllMarketEvents();

        for (int i = 0; i < allMarketEvents.size(); i++) {
            if (allMarketEvents.get(i).getID()==id) {
                return allMarketEvents.get(i);
            }
        }

        return noMarketEvent;
    }


    /*****************************************************/
    //Bids retreving methods
    /*****************************************************/



    public void addBid(Bid bid,int marketEventID){
        Log.d("DB", "Model addBid " + bid);
        ParseObject newBid=bidToJson(bid,marketEventID);
        //		newStudent.saveInBackground();
        try {
            newBid.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /////////////No Background Operation//////////////////

    class BidPlusEvent{
        private Bid bid;
        private MarketEvent marketEvent;

        public Bid getBid() {
            return bid;
        }

        public void setBid(Bid bid) {
            this.bid = bid;
        }

        public MarketEvent getMarketEvent() {
            return marketEvent;
        }

        public void setMarketEvent(MarketEvent marketEvent) {
            this.marketEvent = marketEvent;
        }

        BidPlusEvent(Bid bid, MarketEvent marketEvent) {
            this.bid = bid;
            this.marketEvent = marketEvent;

        }
    }

    public void getAllBids(){
        ArrayList<Bid> allBids = new ArrayList<Bid>();
        ArrayList<BidPlusEvent> allBidsPlusEvents = getAllBidPlusEvent();

        for (int i = 0; i <allBidsPlusEvents.size(); i++) {
                allBids.add(allBidsPlusEvents.get(i).getBid());
        }
        return allBids;
    }

    public ArrayList<BidPlusEvent> getAllBidPlusEvent(){
        Log.d("HA", "Model - Getting all bids");
        ArrayList<BidPlusEvent> allBidsPlusEvents = new ArrayList<BidPlusEvent>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Bids");
        try{
            List<ParseObject> objects=query.find() ;
            if(objects!=null){
                Log.d("HA", "Model - Getting all bids - done (), objects.size()=" +objects.size() );

                for(ParseObject o: objects){
                    allBidsPlusEvents.add(jsonToBidPlusEvent(o));
                }
                Log.d("HA", "Model - after coversion bids.size()=" +allBidsPlusEvents.size());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
            Log.e("HA", "Model - query.find() exeption"+e.toString());
        }
        Log.d("HA", "Model - Getting all bids finished" );

        return allBidsPlusEvents;
    }



    ////////////Helper method to covert from ParseObject to Bid //////////////
    public BidPlusEvent jsonToBidPlusEvent(ParseObject p){
        MarketEvent marketEvent= getMarketEventByID(p.getInt("MarketEventID"));
        Bid bid= new Bid(p.getString("Crop"),p.getString("Bidder"),p.getInt("Price"));
        BidPlusEvent bpe= new BidPlusEvent(bid,marketEvent);
        Log.d("HA", "Model - jsonToBid" +bid );
        return bpe;
    }

    ////////////Helper method to covert from Bid to ParseObject /////////////
    public ParseObject bidToJson(Bid bid, int marketEventID ){
        ParseObject po = new ParseObject("Bids");
        String str= bid.getCrop().toString();
        po.put("Crop",bid.getCrop().toString());
        po.put("Bidder", bid.getWinner());
        po.put("Price", bid.getPrice());
        po.put("MarketEventID", marketEventID);
        return po;
    }

    //Retrieve the object by id

    public Bid getBidByCropAndEventID(String crop, int eventId){
        MarketEvent marketEvent=getMarketEventByID(eventId);
        Bid noBid = new Bid(crop,"no one",999);
        ArrayList<BidPlusEvent> allBids = getAllBidPlusEvent();

        for (int i = 0; i < allBids.size(); i++) {
            if (allBids.get(i).getBid().getCrop().equals(crop)&&allBids.get(i).getMarketEvent().getID()==eventId) {
                return allBids.get(i).getBid();
            }
        }

        return noBid;
    }

    public ArrayList<Bid> getAllBidsByEvent(int eventId){

        ArrayList<Bid> bids = new ArrayList<Bid>();

        ArrayList<BidPlusEvent> allBids = getAllBidPlusEvent();

        for (int i = 0; i < allBids.size(); i++) {
            if (allBids.get(i).getMarketEvent().getID()==eventId) {
                bids.add(allBids.get(i).getBid());
            }
        }
        return bids;
    }



    public void deleteBid(String Name) {
        Log.d("HA", "Model.deleteBid index= " + Name);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bids");
        query.whereEqualTo("index", Name);
        try {
            List<ParseObject> bidList = query.find();
            if (bidList.size() > 0) {
                Log.d("HA", "Model.deleteBid Retrieved " + bidList.size() + " bid");
                bidList.get(0).deleteInBackground();

            }
        } catch (ParseException e1) {
            e1.printStackTrace();
            Log.e("HA", "Model.deleteBid Error: " + e1.getMessage());
        }

    }



}
