package com.example.hidday.directfarming;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MarketEventDetails extends ActionBarActivity {


    private int chosenEvent;
    public static   ArrayList<Bid> bidList;
    private MarketEvent marketEvent;
    public static int idCounter;
    private CustomAdapter adapter;
    private ListView event_bids_list;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_event_details);

        extras = getIntent().getExtras();
        final int position = extras.getInt("Position");
        final int eventID = extras.getInt("EventID");
        chosenEvent=eventID;

        marketEvent=ActiveEvents.marketEventsList.get(position);

        TextView market_event_name = (TextView) findViewById(R.id.event_details_headline);
        TextView market_event_date = (TextView) findViewById(R.id.event_details_subheadline);
        market_event_name.setText(marketEvent.getMarket().getName());

        int day=marketEvent.getDate().getTime().getDay();
        int month=marketEvent.getDate().getTime().getMonth();
        int year=marketEvent.getDate().getTime().getYear();
        year=year%100;
        String dateString= day+"/"+month+"/"+year;
        market_event_date.setText(dateString);

        this.bidList=MainActivity.DB.getAllBidsByEvent(position);

        event_bids_list=(ListView)findViewById(R.id.event_details_list);
        adapter=new CustomAdapter();
        event_bids_list.setAdapter(adapter);

        final Intent market_event_details_intent =new Intent(this, MarketEventDetails.class);


        event_bids_list.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                market_event_details_intent.putExtra("Position",i);
                market_event_details_intent.putExtra("EventID",eventID);
                startActivity(market_event_details_intent);
            }
        });


    }

    private void refreshData(){
        this.bidList=MainActivity.DB.getAllBidsByEvent(chosenEvent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_market_event_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class CustomAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        //constructor for the adapter
        public CustomAdapter(){
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return bidList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return bidList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(final int location, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflater.inflate(R.layout.bid_list_item_layout, parent,false);
            }

            Bid bid_row = bidList.get(location);


            TextView bid_crop = (TextView) convertView.findViewById(R.id.bid_crop_name);
            TextView bid_winner = (TextView) convertView.findViewById(R.id.bid_winner_name);
            TextView bid_price = (TextView) convertView.findViewById(R.id.bid_winning_price);
            ImageButton place_bid =(ImageButton) convertView.findViewById(R.id.bid_row_item_place_bid_button);

            bid_crop.setText(bid_row.getCrop().toString());
            bid_winner.setText(bid_row.getWinner().toString());

            bid_price.setText(""+bid_row.getPrice());



            place_bid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), PlaceBid.class);

                    i.putExtra("Position", location);
                    i.putExtra("EventID",marketEvent.getID());
                    startActivity(i);
                }
            });

            return convertView;
        }

    }
}
