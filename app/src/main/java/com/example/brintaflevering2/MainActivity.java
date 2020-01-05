package com.example.brintaflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static Galgelogik logik = new Galgelogik();
    Button galgeButton, wordListButton;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        galgeButton = findViewById(R.id.galgeButton);
        galgeButton.setOnClickListener(this);
        wordListButton = findViewById(R.id.wordListButton);
        wordListButton.setOnClickListener(this);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(400);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        galgeButton.startAnimation(anim);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.galgeButton:
                Intent intent = new Intent(this,GalgeActivity.class);
                startActivity(intent);
                break;
            case R.id.wordListButton:
                Intent intentWordList = new Intent(this, WordListActivity.class);
                startActivity(intentWordList);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }
}
