package com.example.brintaflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.jinatonic.confetti.CommonConfetti;

public class WinnerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView winnerTextView1, winnerTextView2;
    private Button resetScoreButton, playAgainButton, confettiButton;
    protected ViewGroup container;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        winnerTextView1 = findViewById(R.id.winnerTextView1);
        winnerTextView2 = findViewById(R.id.winnerTextView2);
        resetScoreButton = findViewById(R.id.resetScoresButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        confettiButton = findViewById(R.id.confettiButton);
        resetScoreButton.setOnClickListener(this);
        playAgainButton.setOnClickListener(this);
        confettiButton.setOnClickListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        winnerTextView2.setText("Du er nu oppe på " + preferences.getInt("Wins", 0) +" sejre");
        container = findViewById(R.id.parentView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resetScoresButton:
                preferences.edit().putInt("Wins",0).apply();
                preferences.edit().putInt("Losses",0).apply();
                winnerTextView2.setText("Score nulstillet");
                break;
            case R.id.playAgainButton:
                finish();
                break;
            case R.id.confettiButton:
                CommonConfetti.rainingConfetti(container, new int[] { Color.rgb(212, 175, 55) })
                        .infinite();
                break;

        }

    }

}
