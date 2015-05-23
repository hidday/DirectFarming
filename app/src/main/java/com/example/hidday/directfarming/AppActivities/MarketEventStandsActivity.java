package com.example.hidday.directfarming.AppActivities;

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

import com.example.hidday.directfarming.DBfunctions.DataManager;
import com.example.hidday.directfarming.DataClasses.Bid;
import com.example.hidday.directfarming.DataClasses.Crop;
import com.example.hidday.directfarming.DataClasses.Farmer;
import com.example.hidday.directfarming.DataClasses.Market;
import com.example.hidday.directfarming.DataClasses.MarketEvent;
import com.example.hidday.directfarming.DataClasses.MarketStand;
import com.example.hidday.directfarming.R;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class MarketEventStandsActivity extends ActionBarActivity {


    private int chosenEvent;
    public static ArrayList<MarketStand> stands;
    private MarketEvent marketEvent;

    private CustomAdapter adapter;
    private ListView event_bids_list;
    private Bundle extras;
    private DataManager DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_event_details);

        this.DB= DataManager.getInstance(this);
        stands=new ArrayList<>();
        refreshData();

        extras = getIntent().getExtras();
        final int position = extras.getInt("Position");
        final int eventID = extras.getInt("EventID");
        chosenEvent=eventID;

        marketEvent= ActiveEvents.marketEventsList.get(position);

        TextView market_event_name = (TextView) findViewById(R.id.event_details_headline);
        TextView market_event_date = (TextView) findViewById(R.id.event_details_subheadline);
        market_event_name.setText(marketEvent.getMarket().getName());

        int day=marketEvent.getDate().getTime().getDay();
        int month=marketEvent.getDate().getTime().getMonth();
        int year=marketEvent.getDate().getTime().getYear();
        year=year%100;
        String dateString= day+"/"+month+"/"+year;
        market_event_date.setText(dateString);

       // this.stands=MainActivity.DB.getAllBidsByEvent(position);

        event_bids_list=(ListView)findViewById(R.id.event_details_list);
        adapter=new CustomAdapter();
        event_bids_list.setAdapter(adapter);

        final Intent market_event_details_intent =new Intent(this, MarketEventStandsActivity.class);


        event_bids_list.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                market_event_details_intent.putExtra("Position",i);
                market_event_details_intent.putExtra("EventID",eventID);
                startActivity(market_event_details_intent);
            }
        });


    }

    private void getAllStands(){

    }

    private void refreshData(){

        GregorianCalendar cal= new GregorianCalendar();
        Farmer bidder= new Farmer("Yoni",300207826, "0542193233", "yaacov avinu beersheva");

        MarketEvent market1= new MarketEvent(new Market("Tel Aviv","050784564","hashalom 1","yosi"),cal,1);
        MarketStand stand1= new MarketStand(Crop.Beets, market1);
        MarketStand stand2= new MarketStand(Crop.Broccoli, market1);
        MarketStand stand3= new MarketStand(Crop.Carrot, market1);
        MarketStand stand4= new MarketStand(Crop.Tomato, market1);

        stand1.setCurrentWinner(bidder);
        stand2.setCurrentWinner(bidder);
        stand3.setCurrentWinner(bidder);
        stand4.setCurrentWinner(bidder);

        stand1.setWinningBid(new Bid(bidder, 5));
        stand2.setWinningBid(new Bid(bidder,5));
        stand3.setWinningBid(new Bid(bidder,5));
        stand4.setWinningBid(new Bid(bidder,5));

        this.stands.add(stand1);
        this.stands.add(stand2);
        this.stands.add(stand3);
        this.stands.add(stand4);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
            return stands.size();
        }

        @Override
        public Object getItem(int arg0) {
            return stands.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(final int location, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflater.inflate(R.layout.marketstand_list_item_layout, parent,false);
            }

            MarketStand stand_row = stands.get(location);


            TextView bid_crop = (TextView) convertView.findViewById(R.id.bid_crop_name);
            TextView bid_winner = (TextView) convertView.findViewById(R.id.bid_winner_name);
            TextView bid_price = (TextView) convertView.findViewById(R.id.bid_winning_price);
            ImageButton place_bid =(ImageButton) convertView.findViewById(R.id.bid_row_item_place_bid_button);

            bid_crop.setText(stand_row.getCrop().toString());
            bid_winner.setText(stand_row.getCurrentWinner().toString());

            bid_price.setText(""+stand_row.getWinningBid().getPrice());



            place_bid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), PlaceBidActivity.class);

                    i.putExtra("Position", location);
                    i.putExtra("EventID",marketEvent.getID());
                    startActivity(i);
                }
            });

            return convertView;
        }

    }
}
