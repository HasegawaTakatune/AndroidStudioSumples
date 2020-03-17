package com.example.smplegame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String SHARED_NAME = "GAME_DATA";
    private static final String KEY_HIGH_SCORE = "HIGH_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScpreLabel = findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra(MainActivity.INTENT_SCORE, 0);
        scoreLabel.setText(score + "");

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_NAME, MODE_PRIVATE);
        int highScore = sharedPreferences.getInt(KEY_HIGH_SCORE, 0);

        if (score > highScore) {
            highScpreLabel.setText("High Score : " + score);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_HIGH_SCORE, score);
            editor.apply();
        } else {
            highScpreLabel.setText("High Score : " + highScore);
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void TryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
