package com.example.brintaflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GalgeActivity extends AppCompatActivity implements View.OnClickListener {
    private Galgelogik logik = new Galgelogik();
    private Button gætButton;
    private Button resetButton;
    private EditText gætText;
    private TextView currentOrdTextView, rigtigeOrdTextView,
            brugteBogstaverTextView, winsTextView, lossesTextView;
    private ImageView billede;
    private int winCounter, lossCounter;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge);
        gætButton = findViewById(R.id.gætButton);
        gætButton.setOnClickListener(this);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setVisibility(View.INVISIBLE);
        resetButton.setOnClickListener(this);
        gætText = findViewById(R.id.gætText);
        brugteBogstaverTextView = findViewById(R.id.brugteBogstaverTextView);
        currentOrdTextView = findViewById(R.id.textView);
        currentOrdTextView.setText(logik.getSynligtOrd());
        rigtigeOrdTextView = findViewById(R.id.rigtigeOrdTextView);
        rigtigeOrdTextView.setText(logik.getOrdet());
        //rigtigeOrdTextView.setVisibility(View.INVISIBLE);
        winsTextView = findViewById(R.id.winsTextView);
        lossesTextView = findViewById(R.id.lossesTextView);
        billede = findViewById(R.id.imageView);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        winCounter = preferences.getInt("Wins", 0);
        lossCounter = preferences.getInt("Losses", 0);
        winsTextView.setText("Wins = "+ winCounter);
        lossesTextView.setText("Losses = "+ lossCounter);

        initOnKeyListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        winCounter = preferences.getInt("Wins", 0);
        lossCounter = preferences.getInt("Losses", 0);
        winsTextView.setText("Wins = " + winCounter);
        lossesTextView.setText("Losses = " + lossCounter);
        //updateAfterGame();

    }

    private void initOnKeyListener() {
        gætText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            logik.gætBogstav(gætText.getText().toString());
                            updateTextViews();
                            guessLetter();
                            updateBillede();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gætButton:
                logik.gætBogstav(gætText.getText().toString());
                updateTextViews();
                guessLetter();
                updateBillede();
                break;

            case R.id.resetButton:
                logik.nulstil();
                updateBillede();
                updateTextViews();
                resetButton.setVisibility(View.INVISIBLE);
                gætButton.setVisibility(View.VISIBLE);
                gætText.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void updateTextViews() {
        currentOrdTextView.setText(logik.getSynligtOrd());
        rigtigeOrdTextView.setText(logik.getOrdet());
        String currentWord = "";
        for (String bogstav:
             logik.getBrugteBogstaver()) {
            currentWord = currentWord + bogstav + " ";
        }
        brugteBogstaverTextView.setText("Brugte bogstaver: " + currentWord);
    }

    private void guessLetter() {
        if (!logik.erSidsteBogstavKorrekt() && logik.getBrugteBogstaver().size() < 6) {
            Toast.makeText(this,"Du har brugt " + logik.getBrugteBogstaver().size() +
                    " gæt. " + (6-logik.getBrugteBogstaver().size()) + " tilbage." ,Toast.LENGTH_SHORT).show();
        } else if (logik.erSpilletTabt()) {
            //Toast.makeText(this,"Du har tabt!", Toast.LENGTH_LONG).show();
            lossCounter++;
            updateAfterGame();
            currentOrdTextView.setText(logik.getOrdet());
            Intent loserIntent = new Intent(this,LoserActivity.class);
            loserIntent.putExtra("ORDET", logik.getOrdet());
            startActivity(loserIntent);
        } else if (logik.erSpilletVundet()) {
            //Toast.makeText(this,"Du har vundet!", Toast.LENGTH_LONG).show();
            winCounter++;
            Intent winnerIntent = new Intent(this,WinnerActivity.class);
            startActivity(winnerIntent);
            updateAfterGame();
        }
    }

    private void updateAfterGame() {
        resetButton.setVisibility(View.VISIBLE);
        gætButton.setVisibility(View.INVISIBLE);
        gætText.setVisibility(View.INVISIBLE);
        winsTextView.setText("Wins = " + winCounter);
        lossesTextView.setText("Losses = " + lossCounter);
        preferences.edit().putInt("Wins",winCounter).apply();
        preferences.edit().putInt("Losses",lossCounter).apply();
    }

    public void updateBillede() {
        switch (logik.getAntalForkerteBogstaver()) {
            case 0:
                billede.setImageResource(R.drawable.galge);
                break;

            case 1:
                billede.setImageResource(R.drawable.forkert1);
                break;

            case 2:
                billede.setImageResource(R.drawable.forkert2);
                break;

            case 3:
                billede.setImageResource(R.drawable.forkert3);
                break;

            case 4:
                billede.setImageResource(R.drawable.forkert4);
                break;

            case 5:
                billede.setImageResource(R.drawable.forkert5);
                break;

            case 6:
                billede.setImageResource(R.drawable.forkert6);
                break;
        }

    }
}
