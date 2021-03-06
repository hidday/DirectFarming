package com.example.hidday.directfarming.DBfunctions;

import android.content.Context;

import android.database.sqlite.*;

/**
 * Created by hidday on 28/02/2015.
 */
public class DataManager {

    // class member
    private static DataManager instance;
    private DatabaseHelper myDb;
    private static final int version = 1;
    // private constructor

    private DataManager(Context context){
        myDb = new DatabaseHelper(context);
    }
    //public accessor
    public static DataManager getInstance(Context context){
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    public DatabaseHelper getMyDb() {
        return myDb;
    }


    class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context){
            super(context, "datamodel.db", null , version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table STUDENT (_id INTEGER PRIMARY KEY, name TEXT,phone TEXT, female INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            db.execSQL("DROP TABLE IF EXISTS STUDENT ;");
            onCreate(db);
        }
    }

/*
    private void init(Context context){
        Log.d("DataManager", "Initializing DB ");
        //Parse.initialize(context, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        Parse.initialize(context, "zlDQOcXVpC2a3exWmuvGl3FcWGvsmpn0MgFC7i1D", "I29kas1FRUnzesQxtqegGlpBEoHUDMhmft5QtCDY");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

    }
/*
    public void addMarket(Market market){
        Log.d("DB","DataManager addMarket "+market);
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
        Log.d("HA", "DataManager - Getting all markets");
        ArrayList<Market> markets = new ArrayList<Market>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Markets");
        try{
            List<ParseObject> objects=query.find() ;
            if(objects!=null){
                Log.d("HA", "DataManager - Getting all markets - done (), objects.size()=" +objects.size() );

                for(ParseObject o: objects){
                    markets.add(jsonToMarket(o));
                }
                Log.d("HA", "DataManager - after coversion markets.size()=" +markets.size());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
            Log.e("HA", "DataManager - query.find() exeption"+e.toString());
        }
        Log.d("HA", "DataManager - Getting all markets finished" );

        return markets;
    }


    ////////////Helper method to covert from ParseObject to Student //////////////
    public Market jsonToMarket(ParseObject p){
        Market market= new Market(p.getString("Name"),p.getString("Phone"),p.getString("Address"),p.getString("Manager"));
        Log.d("HA", "DataManager - jsonToMarket" +market );
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
        Log.d("HA", "DataManager.editStudent index= " +s1.getName());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markets");
        query.whereEqualTo("Name", s.getName());
        try{
            List<ParseObject> marketList=query.find();
            if (marketList.size()>0) {
                Log.d("HA", "DataManager.editStudent Retrieved " + marketList.size() + " students");
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
        Log.d("HA", "DataManager.deleteMarket index= " +Name);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markets");
        query.whereEqualTo("index", Name);
        try{
            List<ParseObject> marketList=query.find();
            if (marketList.size()>0) {
                Log.d("HA", "DataManager.deleteMarket Retrieved " + marketList.size() + " market");
                marketList.get(0).deleteInBackground();

            }
        }
        catch (ParseException e1) {
            e1.printStackTrace();
            Log.e("HA", "DataManager.deleteMarket Error: " + e1.getMessage());
        }


    }


    /*****************************************************/
    //Marketevents retreving methods
    /*****************************************************/

   /*
   public void addMarketEvent(MarketEvent marketEvent) {
        Log.d("DB", "DataManager addMarketEvent " + marketEvent);
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
        Log.d("HA", "DataManager - Getting all marketEvents");
        ArrayList<MarketEvent> marketEvents = new ArrayList<MarketEvent>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("MarketEvents");
        try {
            List<ParseObject> objects = query.find();
            if (objects != null) {
                Log.d("HA", "DataManager - Getting all marketEvents - done (), objects.size()=" + objects.size());

                for (ParseObject o : objects) {
                    marketEvents.add(jsonToMarketEvent(o));
                }
                Log.d("HA", "DataManager - after coversion marketEvents.size()=" + marketEvents.size());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("HA", "DataManager - query.find() exeption" + e.toString());
        }
        Log.d("HA", "DataManager - Getting all marketEvents finished");

        return marketEvents;
    }


    ////////////Helper method to covert from ParseObject to Student //////////////
    public MarketEvent jsonToMarketEvent(ParseObject p) {
        Market market= getMarketByName(p.getString("Name"));

        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(p.getDate("Date"));
        MarketEvent marketEvent= new MarketEvent(market,calendar ,p.getInt("ID"));
        Log.d("HA", "DataManager - jsonToMarketEvent" + marketEvent);
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


/*
    public void addBid(Bid bid,int marketEventID){
        Log.d("DB", "DataManager addBid " + bid);
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

    public ArrayList<Bid> getAllBids(){
        ArrayList<Bid> allBids = new ArrayList<Bid>();
        ArrayList<BidPlusEvent> allBidsPlusEvents = getAllBidPlusEvent();

        for (int i = 0; i <allBidsPlusEvents.size(); i++) {
                allBids.add(allBidsPlusEvents.get(i).getBid());
        }
        return allBids;
    }

    public ArrayList<BidPlusEvent> getAllBidPlusEvent(){
        Log.d("HA", "DataManager - Getting all bids");
        ArrayList<BidPlusEvent> allBidsPlusEvents = new ArrayList<BidPlusEvent>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Bids");
        try{
            List<ParseObject> objects=query.find() ;
            if(objects!=null){
                Log.d("HA", "DataManager - Getting all bids - done (), objects.size()=" +objects.size() );

                for(ParseObject o: objects){
                    allBidsPlusEvents.add(jsonToBidPlusEvent(o));
                }
                Log.d("HA", "DataManager - after coversion bids.size()=" +allBidsPlusEvents.size());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
            Log.e("HA", "DataManager - query.find() exeption"+e.toString());
        }
        Log.d("HA", "DataManager - Getting all bids finished" );

        return allBidsPlusEvents;
    }



    ////////////Helper method to covert from ParseObject to Bid //////////////
    public BidPlusEvent jsonToBidPlusEvent(ParseObject p){
        MarketEvent marketEvent= getMarketEventByID(p.getInt("MarketEventID"));
        Bid bid= new Bid(p.getString("Crop"),p.getString("Bidder"),p.getInt("Price"));
        BidPlusEvent bpe= new BidPlusEvent(bid,marketEvent);
        Log.d("HA", "DataManager - jsonToBid" +bid );
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
        Log.d("HA", "DataManager.deleteBid index= " + Name);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bids");
        query.whereEqualTo("index", Name);
        try {
            List<ParseObject> bidList = query.find();
            if (bidList.size() > 0) {
                Log.d("HA", "DataManager.deleteBid Retrieved " + bidList.size() + " bid");
                bidList.get(0).deleteInBackground();

            }
        } catch (ParseException e1) {
            e1.printStackTrace();
            Log.e("HA", "DataManager.deleteBid Error: " + e1.getMessage());
        }

    }


*/
}
