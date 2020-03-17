package com.reryka.shooting;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GameView.EventCallback {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        gameView.setEventCallback(this);
        setContentView(gameView);
    }

    @Override
    public void onGameOver(long score) {
        gameView.stopDrawThread();
        Toast.makeText(this, "Game Over スコア " + score, Toast.LENGTH_LONG).show();
    }
}
