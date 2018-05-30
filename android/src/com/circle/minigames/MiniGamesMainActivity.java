package com.circle.minigames;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.circle.minigames.menuitem.AboutUsActivity;
import com.circle.minigames.menuitem.ContactUsActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import static com.circle.minigames.R.layout.activity_mini_games_main;

public class MiniGamesMainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    public AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(activity_mini_games_main);



        MobileAds.initialize(this, "ca-app-pub-9431796220184727~4791568624");



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9431796220184727/7813225896");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAdView = findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

        });
        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mAdView.loadAd(adRequest);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.nav_home:
                startActivity(new Intent(this, MiniGamesMainActivity.class));
                break;
            case R.id.nav_share:
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String abc = "Mini Games";
                    String sAux = "\nAmazing Games Download and enjoy the most classic and best " +
                            "games." +
                            " Download:\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.circle.minigames";
                    intent.putExtra(Intent.EXTRA_TEXT, abc + sAux);
                    startActivity(Intent.createChooser(intent, ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case R.id.nav_rating:
//                startActivity(new Intent(this, RateUs.class));
                break;
            case R.id.nav_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;


            case R.id.nav_contact_us:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;



        }
        return false;
    }
    public  void AlgorithmicGame(View view){
        Intent intent = new Intent(this, AlgorithmicSumGameActivity.class);
        startActivity(intent);
    }
    public void adsGame(View view){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent intent = new Intent(getApplicationContext(), MyAdsActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Internet Connection Problem.", Toast.LENGTH_SHORT)
                    .show();

        }
    }
    public  void CoinManGame(View view){
        Intent intent = new Intent(this, AndroidLauncher.class);
        intent.putExtra("game", 1);
        startActivity(intent);
    }
    public  void ConnectThree(View view){
        Intent intent = new Intent(this, ConnectThreeActivity.class);
        startActivity(intent);
    }
    public  void FlappyBird(View view){
        Intent intent = new Intent(this, AndroidLauncher.class);
        intent.putExtra("game", 2);
        startActivity(intent);
    }
    public  void HollyWood(View view){

        Intent intent = new Intent(this, HollywoodActivity.class);
        startActivity(intent);
    }
    public  void TicTacToe(View view){
        Intent intent = new Intent(this, TicTacToeActivity.class);
        startActivity(intent);
    }

}
