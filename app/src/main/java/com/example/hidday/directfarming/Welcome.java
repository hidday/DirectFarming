package com.example.hidday.directfarming;

/**
 * Created by Avri on 26/02/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class Welcome extends Activity {

    // Declare Variable
    Button logout;
    Button auctions;
    Button history;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.welcome);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);
        auctions = (Button) findViewById(R.id.auctions);
        history = (Button) findViewById(R.id.history);

        // Logout Button Click Listener
        logout.setOnClickListener(new OnClickListener() {

                                      public void onClick(View arg0) {
                                          // Logout current user
                                          ParseUser.logOut();
                                          finish();
                                      }
                                  });

        auctions.setOnClickListener(new OnClickListener() {

                                      public void onClick(View arg0) {

                                          Intent intent = new Intent(Welcome.this,
                                                  MyAuctions.class);
                                          startActivity(intent);
                                          Toast.makeText(getApplicationContext(),
                                                  "Successfully Logged in",
                                                  Toast.LENGTH_LONG).show();
                                          finish();
                                      }
                                  });





}
}