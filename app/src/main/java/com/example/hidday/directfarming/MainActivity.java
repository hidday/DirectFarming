package com.example.hidday.directfarming;

/**
 * Created by Avri on 26/02/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;


public class MainActivity extends Activity {


    /******************************/
    public static final Farmer myName = new Farmer("Hidday",1,"087534128","yaacov avinu");
    //for testing purposes//
    /*******************************/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add your initialization code here
        Parse.initialize(this, "zlDQOcXVpC2a3exWmuvGl3FcWGvsmpn0MgFC7i1D", "I29kas1FRUnzesQxtqegGlpBEoHUDMhmft5QtCDY");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();



        // Determine whether the current user is an anonymous user
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            // If user is anonymous, send the user to LoginActivity.class
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // Send logged in users to Welcome.class
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                startActivity(intent);
                finish();
            } else {
                // Send user to LoginSignupActivity.class
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }
}
