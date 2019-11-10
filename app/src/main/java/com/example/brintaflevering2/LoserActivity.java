package com.example.brintaflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.jinatonic.confetti.CommonConfetti;

public class LoserActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView loserTextView1, loserTextView2, loserTextView3;
    private Button resetScoreButtonLoser, playAgainButtonLoser;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);

        loserTextView1 = findViewById(R.id.loserTextView1);
        loserTextView2 = findViewById(R.id.loserTextView2);
        loserTextView3 = findViewById(R.id.loserTextView3);
        resetScoreButtonLoser = findViewById(R.id.resetScoresButtonLoser);
        playAgainButtonLoser = findViewById(R.id.playAgainButtonLoser);
        resetScoreButtonLoser.setOnClickListener(this);
        playAgainButtonLoser.setOnClickListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        loserTextView3.setText("Ordet du prøvede at gætte var: " + getIntent().getStringExtra("ORDET"));
        loserTextView2.setText("Du er nu oppe på " + preferences.getInt("Losses", 0) + " nederlag");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resetScoresButtonLoser:
                preferences.edit().putInt("Losses", 0).apply();
                loserTextView2.setText("Score nulstillet");
                break;
            case R.id.playAgainButtonLoser:
                finish();
                break;
        }

    }

}
