package com.circle.minigames;

import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Integer gameSelection;
		gameSelection = getIntent().getIntExtra("game", 1);
		if (gameSelection == 1){
			CoinMan();
		}else {
			FlappyBird();
		}

		Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
	}

	public void FlappyBird(){
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FlappyBird(), config);
	}
	public void CoinMan(){
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new CoinMan(), config);
	}
}
