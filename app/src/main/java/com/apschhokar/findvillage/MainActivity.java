package com.apschhokar.findvillage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.digits.sdk.android.LogoutEventDetails;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.GuestCallback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import io.fabric.sdk.android.Fabric;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;


import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "6sPufae5izNWaAK47cdQzETs3";
    private static final String TWITTER_SECRET = "k5DTtn2lJ8pzGUepLc96Tnatcrklxia4peavISPcbNfg91Drla";
    private static final String UserTweets = "UserTweet";

    private TwitterLoginButton loginButton;
    public static String username = "";
    public static Long userID;


    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";

                username = session.getUserName();
                userID = session.getUserId();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: i am called" + requestCode + " " + resultCode +" " + data.toString());
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);




        TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
            @Override
            public void success(Result<AppSession> result) {

                AppSession session = result.data;
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
                twitterApiClient.getStatusesService().userTimeline(userID, username, 20, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {

                        //create an arraylist to store all the tweets and then pass it to next activity
                        ArrayList<String> tweets = new ArrayList<>();

                        for (Tweet tweet : listResult.data) {
                            // here you will get list
                            Log.d(TAG, "success: ----> " + tweet.text);
                            tweets.add(tweet.text);
                        }





//                        Intent myIntent = new Intent(MainActivity.this, EnterLocation.class);
//
//                        String[] tweetsToSend = new String[tweets.size()];
//                        int count = 0;
//                        for (String s : tweets){
//                            tweetsToSend[count] = tweets.get(count);
//                            count++;
//                        }
//
//
//                        Bundle b=new Bundle();
//                        b.putStringArray(UserTweets,tweetsToSend );
//                        Intent i=new Intent(getApplicationContext(), MainActivity.class);
//                        i.putExtras(b);
//
//                        MainActivity.this.startActivity(i);

                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void failure(TwitterException e) {
                Log.e(TAG, "Load Tweet failure", e);
            }
        });


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
