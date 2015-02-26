package com.example.hidday.directfarming;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MarketEventDetails extends ActionBarActivity {


    private MarketEvent marketEvent;
    public static int idCounter;
    private ListView market_event_list;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_event_details);



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
        private  ArrayList<Bid> rowList;


        //constructor for the adapter
        public CustomAdapter(){
            inflater = LayoutInflater.from(getApplicationContext());

            for(int i=0;i<marketEvent.getBidList().size();i++)
            {
            rowList.add(new Bid(marketEvent.getBidList().get(i),marketEvent.))
            }

        }

        @Override
        public int getCount() {
            return marketEventsList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return marketEventsList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int location, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflater.inflate(R.layout.event_list_item_layout, parent,false);
            }
            MarketEvent event_list = marketEventsList.get(location);

            TextView event_location = (TextView) convertView.findViewById(R.id.eventlist_event_location);
            TextView event_date = (TextView) convertView.findViewById(R.id.eventlist_event_date);

            event_location.setText(event_list.getMarket().getName());
            event_date.setText(event_list.getDate().toString());

            return convertView;
        }

    }
}
