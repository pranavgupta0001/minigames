package com.circle.minigames;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HollywoodActivity extends AppCompatActivity {
    ArrayList<String> celebURLs = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int chosenCeleb = 0;
    String[] answers = new String[4];
    int locationOfCorrectAnswer = 0;
    ImageView imageView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ProgressDialog pDialog;


    public void newQuestion() {

            hideDialog();

        try {
            Random rand = new Random();

            chosenCeleb = rand.nextInt(celebURLs.size());

            ImageDownloader imageTask = new ImageDownloader();

            Bitmap celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();
            imageView.setImageBitmap(celebImage);

            locationOfCorrectAnswer = rand.nextInt(4);
            int incorrectLocation;
            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {

                    answers[i] = celebNames.get(chosenCeleb);

                } else {
                    incorrectLocation = rand.nextInt(celebURLs.size());
                    while (incorrectLocation == chosenCeleb) {
                        incorrectLocation = rand.nextInt(celebURLs.size());
                    }
                    answers[i] = celebNames.get(incorrectLocation);
                }
            }
            button0.setText(answers[0]);
            button1.setText(answers[1]);
            button2.setText(answers[2]);
            button3.setText(answers[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void celebChosen(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong. Correct is " + celebNames.get(chosenCeleb), Toast.LENGTH_LONG).show();
        }
        newQuestion();

    }


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap mtBitmap = BitmapFactory.decodeStream(inputStream);
                return mtBitmap;
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();

                hideDialog();


                return null;
            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hollywood);
//
        pDialog = new ProgressDialog(this, R.style.StyledDialog);
        pDialog.setCancelable(false);
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        setTitle("HollyWood");
    }

    public void StartHollywood(View view) {

        showDialog();
        pDialog.setMessage("Loading ...");
        if (haveNetworkConnection()) {
            TextView textView = findViewById(R.id.goTextView);
            textView.setVisibility(View.INVISIBLE);
            LinearLayout linearLayout = findViewById(R.id.linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            Snackbar.make(view, "Loading Hollywood...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            DownloadTask task = new DownloadTask();
            String result = null;
            imageView = findViewById(R.id.imageView);
            button0 = findViewById(R.id.button0);
            button1 = findViewById(R.id.button1);
            button2 = findViewById(R.id.button2);
            button3 = findViewById(R.id.button3);
            try {
                result = task.execute("http://www.posh24.se/kandisar").get();
                String[] splitResult = result.split("<div class=\"sidebarContainer\">");

                Pattern p = Pattern.compile("img src=\"(.*?)\"");
                Matcher m = p.matcher(splitResult[0]);
                while (m.find()) {
                    celebURLs.add(m.group(1));
                }
                p = Pattern.compile("alt=\"(.*?)\"");
                m = p.matcher(splitResult[0]);
                while (m.find()) {
                    celebNames.add(m.group(1));
                }
                newQuestion();


            } catch (Exception e) {
                e.printStackTrace();

            }
        }else {
            Toast.makeText(this, "Internet Connection Problem. Try Again", Toast.LENGTH_SHORT).show();
            hideDialog();
        }
        //hiding the progressbar after completion
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
