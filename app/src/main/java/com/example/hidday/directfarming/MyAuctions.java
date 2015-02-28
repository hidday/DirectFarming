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

import java.util.Date;
import java.util.List;


public class MyAuctions extends ActionBarActivity {
    List<ParseObject> Avri;
    TextView user_name1;
    TextView user_name2;
    TextView user_name3;
    Date date;
    String marketname1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_auctions);

        user_name1 = (TextView)findViewById(R.id.user_name1);
        user_name2 = (TextView)findViewById(R.id.user_name2);
        user_name3 = (TextView)findViewById(R.id.user_name3);


        final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("MarketEvents");
        query.findInBackground (new FindCallback<ParseObject>(){
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        if (objects.get(i) != null) {
                            ParseObject p = objects.get(0);
                            marketname1 = p.getString("Name");
                            user_name1.setText(marketname1);

                            ParseObject z = objects.get(1);
                            marketname1 = z.getString("Name");
                            user_name2.setText(marketname1);

                            ParseObject q = objects.get(2);
                            marketname1 = q.getString("Name");
                            user_name3.setText(marketname1);

                            Log.d("user_Name", "Retrieved " + objects.size() + " Users");
                        } else {
                            Log.d("user_Name", "Error: " + e.getMessage());
                        }
                    }}
                else {
                    Toast.makeText(getApplicationContext(),
                            "not working",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
        );



    }
    }