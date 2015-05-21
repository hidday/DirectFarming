package com.example.hidday.directfarming;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PlaceBid extends ActionBarActivity {

    Bundle extras;
    MarketStand selectedStand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_bid);

        extras = getIntent().getExtras();
        final int position = extras.getInt("Position");
        final int eventID = extras.getInt("EventID");

        this.selectedStand=MarketEventDetails.stands.get(position);

        TextView crop_name = (TextView) findViewById(R.id.place_bid_crop_name);
        TextView previous_price = (TextView) findViewById(R.id.place_bid_previous_price);
        final EditText new_price = (EditText) findViewById(R.id.place_bid_new_price);
        Button place_bid_button= (Button) findViewById(R.id.place_bid_button);


        crop_name.setText(selectedStand.getCrop().toString());
        previous_price.setText(""+selectedStand.getWinningBid().getPrice());

        place_bid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int bidPrice;
                String str=new_price.getText().toString();
                if(str.isEmpty())
                {
                    bidPrice=999;
                }
                else{
                    bidPrice=Integer.parseInt(new_price.getText().toString());
                }

             //   selectedStand.placeBid(MainActivity.myName, bidPrice, eventID);

                Intent i = new Intent(getApplicationContext(), ActiveEvents.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_bid, menu);
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
}
