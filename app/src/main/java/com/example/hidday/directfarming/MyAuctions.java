package com.example.hidday.directfarming;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MyAuctions extends ActionBarActivity {
    List<ParseObject> Avri;
    TextView user_name1;
    TextView user_name2;
    TextView user_name3;
    Date date;
    String marketname1;
    ArrayList<MarketEvent> marketEventListFromParse = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_auctions);

        user_name1 = (TextView) findViewById(R.id.user_name1);
        user_name2 = (TextView) findViewById(R.id.user_name2);
        user_name3 = (TextView) findViewById(R.id.user_name3);


        final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("MarketEvents");
        query.findInBackground(new FindCallback<ParseObject>() {
                                   @Override
                                   public void done(List<ParseObject> objects, ParseException e) {
                                       if (e == null) {
                                           for (ParseObject o : objects) {
                                               marketEventListFromParse.add(o);
                                           }
                                       } else {
                                           Toast.makeText(getApplicationContext(),
                                                   "not working",
                                                   Toast.LENGTH_LONG).show();
                                       }
                                   }
                               }
        );


    }


    ////////////Helper method to covert from ParseObject to Student //////////////
    public Market jsonToMarket(ParseObject p) {

        MarketEvent marketEvent = new MarketEvent(p.getString("Name"), p.getString("Date"));
        Log.d("HY", "Model - jsonToStudent" + s);
        return s;
    }

}
