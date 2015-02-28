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

    TextView user_name1;
    TextView user_name2;
    TextView user_name3;
    String marketname1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_auctions);

        user_name1 = (TextView)findViewById(R.id.user_name1);
        user_name2 = (TextView)findViewById(R.id.user_name2);
        user_name3 = (TextView)findViewById(R.id.user_name3);


        Market marketTest;
        marketTest= MainActivity.DB.getMarketByName("Tel aviv");

        ArrayList<Market> marketList1=new ArrayList<>();

        marketList1= MainActivity.DB.getAllMarkets();

        marketTest=marketList1.get(1);

        marketname1=marketTest.getName();

        Bid bidTest;
        bidTest= MainActivity.DB.getBidByCropAndID("Tomato",3);

        ArrayList<Bid> bidList1=new ArrayList<>();

        bidList1=MainActivity.DB.getAllBids();



        user_name1.setText(marketname1+"oved");

        user_name2.setText(marketname1);

        user_name3.setText(marketname1);


    }


   }