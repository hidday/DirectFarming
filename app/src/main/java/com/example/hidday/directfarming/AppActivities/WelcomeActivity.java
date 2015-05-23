package com.example.hidday.directfarming.AppActivities;

/**
 * Created by Avri on 26/02/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hidday.directfarming.DBfunctions.UserFunctions;
import com.example.hidday.directfarming.R;

public class WelcomeActivity extends Activity {


        UserFunctions userFunctions;
        Button btnLogout;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            /**
             * Dashboard Screen for the application
             * */
            // Check login status in database
            userFunctions = new UserFunctions();
            if(userFunctions.isUserLoggedIn(getApplicationContext())){
                setContentView(R.layout.welcome);
                btnLogout = (Button) findViewById(R.id.logout);

                btnLogout.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        userFunctions.logoutUser(getApplicationContext());
                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login);
                        // Closing dashboard screen
                        finish();
                    }
                });

            }else{
                // user is not logged in show login screen
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                // Closing dashboard screen
                finish();
            }




        }
    }

/*
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

                                          Intent intent = new Intent(WelcomeActivity.this,
                                                  ActiveEvents.class);
                                          startActivity(intent);
                                          Toast.makeText(getApplicationContext(),
                                                  "Successfully Logged in",
                                                  Toast.LENGTH_LONG).show();
                                          finish();
                                      }
                                  });





}
}

*/