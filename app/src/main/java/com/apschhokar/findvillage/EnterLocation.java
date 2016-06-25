package com.apschhokar.findvillage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class EnterLocation extends AppCompatActivity {

    private static final String UserTweets = "UserTweet";
    private static final String TAG = "EnterLocation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_location);

        Intent intent = getIntent();
        Bundle b=this.getIntent().getExtras();
        String[] array=b.getStringArray(UserTweets);

        for (int i =0 ; i< array.length;i++){
            Log.d(TAG, "onCreate: " + array[i]);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();


    }
}
