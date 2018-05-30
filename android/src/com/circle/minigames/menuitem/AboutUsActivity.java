package com.circle.minigames.menuitem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.circle.minigames.MiniGamesMainActivity;
import com.circle.minigames.R;

public class AboutUsActivity extends AppCompatActivity {
    Intent intent;
    TextView peragraph1, peragraph2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        peragraph1 =findViewById(R.id.pera1);
        peragraph2=findViewById(R.id.pera2);

        peragraph1.setText("The world is full of online games but sometimes you just want to play games which are classic like Tic Tac Toe and can be played offline without annoying adds. \n" +
                "Instead of downloading one app per little game which also need your internet connection to work and then end up wasting half of your precious time on the annoying advertisement, you can download this, Mini Games app which has all the games you love in one place in very small size app.");
        peragraph2.setText("We are always trying to improve the game and kill bugs, if you found " +
                "any problem please feel free to share with us.");

    }

    @Override
    public void onBackPressed() {
        intent = new Intent(this, MiniGamesMainActivity.class);
        startActivity(intent);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.finish();
    }
}
