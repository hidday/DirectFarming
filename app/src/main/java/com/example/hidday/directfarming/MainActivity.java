package com.example.hidday.directfarming;

/**
 * Created by Avri on 26/02/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;


public class MainActivity extends Activity {
    public static Model DB;

    /******************************/
    public static String myName="free";
    //for testing purposes//
    /*******************************/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        DB=Model.getInstance(this);

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
                myName=currentUser.getUsername();
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
