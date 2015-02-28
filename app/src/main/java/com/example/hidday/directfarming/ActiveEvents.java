package com.example.hidday.directfarming;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


public class ActiveEvents extends ActionBarActivity {

    public static ArrayList<MarketEvent> marketEventsList=new ArrayList<>();
    public static int idCounter;
    private ListView market_event_list;
    private CustomAdapter adapter;

    private static boolean flagFirst=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_events);

        idCounter=0;

        if(flagFirst==true){
            createInitialEventList();
            flagFirst=false;
        }



        market_event_list=(ListView)findViewById(R.id.active_market_event_list);
        adapter=new CustomAdapter();
        market_event_list.setAdapter(adapter);

        final Intent market_event_details_intent =new Intent(getApplicationContext(), MarketEventDetails.class);

        market_event_list.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {

                market_event_details_intent.putExtra("Position",i);
                startActivity(market_event_details_intent);
            }
        });

    }


    private void createInitialEventList() {


        GregorianCalendar date1= new GregorianCalendar (2015,3,14);
        GregorianCalendar date2= new GregorianCalendar (2015,5,2);
        GregorianCalendar date3= new GregorianCalendar (2015,11,23);

        marketEventsList.add(new MarketEvent(new Market("BeerSheva", "089875266","Yaacov Avinu 8","Kama Yosi"),date1 ));
        marketEventsList.add(new MarketEvent(new Market("Tel Aviv", "035875266","Eben Gevirol 5","Dudu"),date2 ));
        marketEventsList.add(new MarketEvent(new Market("Haifa", "049885266","Haatzmaut 82","Yonatan"),date3 ));

        ArrayList<Bid> bidList= new ArrayList<>();
        bidList.add(new Bid(Crop.Tomato));
        marketEventsList.get(1).setBidList(bidList);
        marketEventsList.get(2).setBidList(bidList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_active_auctions, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        market_event_list.setAdapter(adapter);
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

        public CustomAdapter(){
            inflater = LayoutInflater.from(getApplicationContext());
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
        public View getView(final int location, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflater.inflate(R.layout.event_list_item_layout, parent,false);
            }
            MarketEvent event_row = marketEventsList.get(location);

            TextView event_location = (TextView) convertView.findViewById(R.id.eventlist_event_location);
            TextView event_date = (TextView) convertView.findViewById(R.id.eventlist_event_date);
            ImageButton button= (ImageButton) convertView.findViewById(R.id.event_list_button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("button click", "BITCH"+location);

                    Intent market_event_details_intent =new Intent(getApplicationContext(), MarketEventDetails.class);

                    market_event_details_intent.putExtra("Position",location);
                    startActivity(market_event_details_intent);
                }
            });

            int day=event_row.getDate().getTime().getDay();
            int month=event_row.getDate().getTime().getMonth();
            int year=event_row.getDate().getTime().getYear();
            year=year%100;

            String dateString= day+"/"+month+"/"+year;

            Log.d("market location",event_row.getMarket().getName());
            Log.d("market date",dateString);

            event_location.setText(event_row.getMarket().getName());
            event_date.setText(dateString);

            return convertView;
        }

    }

}
