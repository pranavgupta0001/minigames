package com.circle.minigames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import static com.circle.minigames.R.layout.activity_mini_games_main;

public class MiniGamesMainActivity extends AppCompatActivity {
    TextView algorithmicGameTextView;
    TextView coinManTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        setContentView(activity_mini_games_main);
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

        algorithmicGameTextView = findViewById(R.id.algorithmicGameTextView);
        coinManTextView = findViewById(R.id.coinManTextView);


    }

    public  void AlgorithmicGame(View view){
        Intent intent = new Intent(this, AlgorithmicSumGameActivity.class);
        startActivity(intent);
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



}
