package com.apschhokar.findvillage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

public class ProfileInsights extends AppCompatActivity {

    private static final String UserTweets = "TwitterText";
    private static final String TAG = "EnterLocation";
    private static final String WATSON_PASSWORD =  "Me6TGdzFhmDH";
    private static final String WATSON_USERNAME = "3a16b4c7-f66f-4092-a067-0235e5f7b6ee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_location);

        Intent intent = getIntent();
        String value = intent.getStringExtra(UserTweets); //if it's a string you stored.

        Log.d(TAG, "onCreate: ------->" + value);

        getProfileInsights(value);

    }

    public void getProfileInsights(String value){
        final String text = value;

        Thread thread = new Thread(new Runnable(){
            public void run() {
                try {
                    PersonalityInsights service = new PersonalityInsights();
                    service.setUsernameAndPassword(WATSON_USERNAME, WATSON_PASSWORD);

                    Profile profile = service.getProfile(text).execute();
                    Log.d(TAG, "getProfileInsights: ---->" + profile);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }



    @Override
    protected void onResume() {
        super.onResume();


    }
}
