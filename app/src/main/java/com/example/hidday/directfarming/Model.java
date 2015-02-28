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

    /*
    /////////////CallBack Interface //////////////////
    interface GetAllClbck{
        public void done(List<Market> markets);
    }
    /////////////Find In Background Operation with CallBack//////////////////
    public void getAllMarkets2(GetAllClbck clbck){
        final GetAllClbck getAllListener=clbck;

        Log.d("HA", "Model - Getting all markets");

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Markets");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                Log.d("HA", "Model - Getting all markets - done ()" );
                List<Market> markets = new ArrayList<Market>();
                for(ParseObject o: objects){
                    markets.add(jsonToMarket(o));
                }
                getAllListener.done(markets);
                Log.d("HA", "Model after getAllListener.done()" );
            }

        });
        Log.d("HA", "Model Getting all markets -after findInBackground ()" );

    }
    */

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
        Log.d("HA", "Model.editStudent index= " +s1.getName());
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
    Market market;
    public Market getMarketByName(String Name){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markets");
        query.whereEqualTo("Name", Name);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> marketList, ParseException e) {
                if (e == null) {
                    Log.d("HA", "Model.getMarketByName Retrieved " + marketList.size() + " markets");
                    market=jsonToMarket(marketList.get(0));

                } else {
                    Log.d("HA", "Model.getMarketByName Error: " + e.getMessage());
                }
            }
        });
        return market;
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
    //Bids retreving methods
    /*****************************************************/



    public void addBid(Bid bid){
        Log.d("DB", "Model addBid " + bid);
        ParseObject newBid=bidToJson(bid);
        //		newStudent.saveInBackground();
        try {
            newBid.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /////////////No Background Operation//////////////////

    public ArrayList<Bid> getAllBids(){
        Log.d("HA", "Model - Getting all bids");
        ArrayList<Bid> bids = new ArrayList<Bid>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Bids");
        try{
            List<ParseObject> objects=query.find() ;
            if(objects!=null){
                Log.d("HA", "Model - Getting all bids - done (), objects.size()=" +objects.size() );

                for(ParseObject o: objects){
                    bids.add(jsonToBid(o));
                }
                Log.d("HA", "Model - after coversion bids.size()=" +bids.size());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
            Log.e("HA", "Model - query.find() exeption"+e.toString());
        }
        Log.d("HA", "Model - Getting all bids finished" );

        return bids;
    }



    ////////////Helper method to covert from ParseObject to Bid //////////////
    public Bid jsonToBid(ParseObject p){

        Bid bid= new Bid(p.getString("Crop"),p.getString("Bidder"),p.getInt("Price"));
        Log.d("HA", "Model - jsonToBid" +bid );
        return bid;
    }

    ////////////Helper method to covert from Bid to ParseObject /////////////
    public ParseObject bidToJson(Bid bid){
        ParseObject po = new ParseObject("Bid");
        po.put("Crop",bid.getCrop());
        po.put("Bidder", bid.getWinner());
        po.put("Price", bid.getPrice());
        return po;
    }


    public void editBid(Bid bid,MarketEvent marketEvent) {
        final Bid bid1=bid;
        ParseObject bidToEdit=null;
        Log.d("HA", "Model.editStudent index= " +bid.getCrop());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bids");
        query.whereEqualTo("Crop", bid.getCrop());
        query.whereEqualTo("MarketEventID",marketEvent.getID());
        try{
            List<ParseObject> bidList=query.find();
            if (bidList.size()>0) {
                Log.d("HA", "Model.editStudent Retrieved " + bidList.size() + " students");
                bidToEdit=bidList.get(0);
                bidToEdit.put("Crop",bid1.getCrop());
                bidToEdit.put("Price",bid1.getPrice());

                //bidder and winner are not the same- to be fixed
                bidToEdit.put("Bidder",bid1.getWinner());
                bidToEdit.put("MarketEventID",marketEvent.getID());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
        }


        bidToEdit.saveInBackground();
    }

    //Retrieve the object by id
    Bid bid;
    public Bid getBidByCropAndID(String crop, int eventId){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bids");
        query.whereEqualTo("Crop", crop);
        query.whereEqualTo("MarketEventID", eventId);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> bidList, ParseException e) {
                if (e == null) {
                    Log.d("HA", "Model.getBidByName Retrieved " + bidList.size() + " bids");
                    bid=jsonToBid(bidList.get(0));

                } else {
                    Log.d("HA", "Model.getBidByName Error: " + e.getMessage());
                }
            }
        });
        return bid;
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
