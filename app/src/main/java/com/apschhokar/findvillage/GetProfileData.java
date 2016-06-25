package com.apschhokar.findvillage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "6sPufae5izNWaAK47cdQzETs3";
    private static final String TWITTER_SECRET = "k5DTtn2lJ8pzGUepLc96Tnatcrklxia4peavISPcbNfg91Drla";

    private TwitterLoginButton loginButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true)
                        .setOAuthConsumerKey("B***************Q")
                        .setOAuthConsumerSecret(
                                "l*********************************o")
                        .setOAuthAccessToken(
                                "1*******************************X")
                        .setOAuthAccessTokenSecret(
                                "1***************************c");
                TwitterFactory tf = new TwitterFactory(cb.build());
                Twitter twitter = tf.getInstance();
                try {
                    List<Status> statuses;
                    String user;
                    user = "replace with the user name of your choice";
                    statuses = twitter.getUserTimeline(user);
                    Log.i("Status Count", statuses.size() + " Feeds");
                    for (int i = 0; i < statuses.size(); i++) {
                        Status status = statuses.get(i);
                        Log.i("Tweet Count " + (i + 1), status.getText() + "\n\n");
                    }
                } catch (TwitterException te) {
                    te.printStackTrace();
                }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
