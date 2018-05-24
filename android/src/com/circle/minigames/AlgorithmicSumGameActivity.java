package com.circle.minigames;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class AlgorithmicSumGameActivity extends AppCompatActivity {

    Button goBut;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int noqu = 0;
    TextView scoreTextView;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button resetButton;
    ConstraintLayout gameLayout;

    public void reset(View view){
        resetButton.setVisibility(View.INVISIBLE);
        score = 0;
        noqu = 0 ;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noqu));
        newqu();

        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                resetButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void start(View view) {
        goBut.setVisibility(View.INVISIBLE);
        reset(findViewById(R.id.sumTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newqu() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answer.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                int x = random.nextInt(44);
                while (x == a + b) {
                    x = random.nextInt(44);
                }
                answer.add(x);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Right :)");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
            Log.i("Wrong", "Wrong answer");
        }
        noqu++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noqu));
        newqu();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithmic_sum_game);
        goBut = findViewById(R.id.start);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        resultTextView = findViewById(R.id.resultTextView);
        sumTextView = findViewById(R.id.sumTextView);
        gameLayout = findViewById(R.id.gameLayout);


        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resetButton = findViewById(R.id.resetButton);
        gameLayout.setVisibility(View.INVISIBLE);
        goBut.setVisibility(View.VISIBLE);


    }
}
