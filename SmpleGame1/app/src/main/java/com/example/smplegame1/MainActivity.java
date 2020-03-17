package com.example.smplegame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static final String INTENT_SCORE = "SCORE";

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;

    private SoundPlayer soundPlayer;

    private GameObject objectOrange;
    private GameObject objectPink;
    private GameObject objectBlack;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private int frameHeight;
    private int boxSize;
    private int screenWidth;
    private int screenHeight;

    private int score;

    private float boxY;
    private int boxSpeed;

    private boolean canAction = false;
    private boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundPlayer = new SoundPlayer(this);

        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        box = findViewById(R.id.box);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        boxSpeed = Math.round(screenHeight / 60);
        objectOrange = new GameObject((ImageView) findViewById(R.id.orange), Math.round(screenWidth / 60f), screenWidth + 20, GameObject.TYPE_ITEM);
        objectPink = new GameObject((ImageView) findViewById(R.id.pink), Math.round(screenWidth / 36f), screenWidth + 5000, GameObject.TYPE_ITEM);
        objectBlack = new GameObject((ImageView) findViewById(R.id.black), Math.round(screenWidth / 45f), screenWidth + 20, GameObject.TYPE_ENEMY);

        scoreLabel.setText("Score : 0");
    }

    private void changePos() {

        hitCheck();

        objectOrange.Move();
        objectPink.Move();
        objectBlack.Move();

        if (canAction) boxY -= boxSpeed;
        else boxY += boxSpeed;

        if (boxY < 0) boxY = 0;
        if (boxY > frameHeight - boxSize) boxY = frameHeight - boxSize;

        box.setY(boxY);

        scoreLabel.setText("Score : " + score);
    }

    public void hitCheck() {

        objectOrange.HitCheck(
                new Runnable() {
                    @Override
                    public void run() {
                        score += 10;
                        soundPlayer.PlayHitSound();
                    }
                }, boxY, boxSize);

        objectPink.HitCheck(new Runnable() {
            @Override
            public void run() {
                score += 30;
                soundPlayer.PlayHitSound();
            }
        }, boxY, boxSize);

        objectBlack.HitCheck(new Runnable() {
            @Override
            public void run() {
                soundPlayer.PlayOverSound();

                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(INTENT_SCORE, score);
                startActivity(intent);
            }
        }, boxY, boxSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isStart) {
            isStart = true;

            FrameLayout frame = findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            objectOrange.Init(frameHeight);
            objectPink.Init(frameHeight);
            objectBlack.Init(frameHeight);

            boxY = box.getY();
            boxSize = box.getHeight();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            canAction = true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            canAction = false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
    }
}
