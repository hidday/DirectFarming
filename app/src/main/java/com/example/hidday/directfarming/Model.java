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

    /*/////////////CallBack Interface //////////////////
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

    }*/

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

//		try {
//			studentToEdit.save();
//
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//			Log.e("HY", "Model.editStudent Error: " + e1.getMessage());
//		}



    }

     //Retrieve the object by name
    Market s;
    public Market getMarketByName(String Name){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Markets");
        query.whereEqualTo("_Name", Name);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> marketList, ParseException e) {
                if (e == null) {
                    Log.d("HA", "Model.getMarketByName Retrieved " + marketList.size() + " markets");
                    s=jsonToMarket(marketList.get(0));

                } else {
                    Log.d("HA", "Model.getMarketByName Error: " + e.getMessage());
                }
            }
        });
        return s;
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

/////////////No Background Operation//////////////////

    public ArrayList<MarketEvent> getAllMarketEvents(){
        Log.d("HA", "Model - Getting all marketEvents");
        ArrayList<MarketEvent> marketEvents = new ArrayList<MarketEvent>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("MarketEvents");
        try{
            List<ParseObject> objects=query.find() ;
            if(objects!=null){
                Log.d("HA", "Model - Getting all marketEvents - done (), objects.size()=" +objects.size() );

                for(ParseObject o: objects){
                    marketEvents.add(jsonToMarketEvent(o));
                }
                Log.d("HA", "Model - after coversion marketEvents.size()=" +marketEvents.size());
            }
        }
        catch(ParseException e){
            e.printStackTrace();
            Log.e("HA", "Model - query.find() exeption"+e.toString());
        }
        Log.d("HA", "Model - Getting all markets finished" );

        return marketEvents;
    }
    ////////////Helper method to covert from ParseObject to Student //////////////
    public MarketEvent jsonToMarketEvent(ParseObject p){
        MarketEvent marketEvent= new MarketEvent(p.getInt("ID"),p.getString("Name"),p.getDate("Date"));
        Log.d("HA", "Model - jsonToMarket" +marketEvent );
        return marketEvent;
    }

    public void deleteMarketEvent(int ID){
        Log.d("HA", "Model.deleteMarketEvnet index= " +ID);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MarketEvents");
        query.whereEqualTo("ID", ID);
        try{
            List<ParseObject> marketEventsList=query.find();
            if (marketEventsList.size()>0) {
                Log.d("HA", "Model.deleteMarketEvent Retrieved " + marketEventsList.size() + " marketEvnet");
                marketEventsList.get(0).deleteInBackground();

            }
        }
        catch (ParseException e1) {
            e1.printStackTrace();
            Log.e("HA", "Model.deleteMarketEvent Error: " + e1.getMessage());
        }


    }

    //Retrieve the object by id
    MarketEvent s;
    public MarketEvent getMarketEventByID(int ID){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MarketEvents");
        query.whereEqualTo("ID", ID);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> marketEventList, ParseException e) {
                if (e == null) {
                    Log.d("HA", "Model.getMarketByName Retrieved " + marketEventList.size() + " markets");
                    s=marketEventToJson(marketEventList.get(0));

                } else {
                    Log.d("HA", "Model.getMarketByName Error: " + e.getMessage());
                }
            }
        });
        return s;
    }



    public void editMarketEvent(MarketEvent s) {
        final MarketEvent s1 = s;
        ParseObject MarketEventToEdit = null;
        Log.d("HA", "Model.editMarketEvent index= " + s1.getMarket().getName());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MarketEvents");
        query.whereEqualTo("Is", s.getMarket().getName());
        try {
            List<ParseObject> marketEventList = query.find();
            if (marketEventList.size() > 0) {
                Log.d("HA", "Model.editMarketEvent Retrieved " + marketEventList.size() + " students");
                MarketEventToEdit = marketEventList.get(0);
                MarketEventToEdit.put("ID", s1.getID());
                MarketEventToEdit.put("Name", s1.getMarket().getName());
                MarketEventToEdit.put("Date", s1.getDate());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
        public void addMarketEvent(MarketEvent marketEvent){
            Log.d("DB","Model addMarketEvent "+marketEvent);
            ParseObject newMarkeEvent = marketEventToJson(marketEvent);
            try {
                newMarkeEvent.save();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    public ParseObject marketEventToJson(MarketEvent marketEvent){
        ParseObject po = new ParseObject("marketEvent");
        po.put("ID",marketEvent.getID());
        po.put("Name",marketEvent.getMarket().getName());
        po.put("Date", marketEvent.getDate();
        return po;
    }

    }
