package com.example.smplegame1;

import android.widget.ImageView;

public class GameObject {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_ENEMY = 1;

    private int objectType;

    private ImageView imageView;

    private float speed;
    private float spawnPoint;

    private int frameHeight;
    private float positionX;
    private float positionY;

    private boolean doOnce = false;


    public GameObject(ImageView imageView, float speed, float spawnPoint, int objectType) {
        this.imageView = imageView;
        this.speed = speed;
        this.spawnPoint = spawnPoint;
        this.objectType = objectType;

        this.imageView.setX(-80.0f);
        this.imageView.setY(-80.0f);
    }

    public void Init(int frameHeight) {
        positionX = imageView.getX();
        positionY = imageView.getY();
        this.frameHeight = frameHeight;
    }

    public void Move() {
        positionX -= speed;
        if (positionX < 0) {
            Respawn();
        }
        imageView.setX(positionX);
        imageView.setY(positionY);
    }

    public int HitCheck(Runnable runnable, float targetY, int targetSize) {

        float centerX = positionX + imageView.getWidth() / 2;
        float centerY = positionY + imageView.getHeight() / 2;

        if (!doOnce && 0 <= centerX && centerX <= targetSize && targetY <= centerY && centerY <= targetY + targetSize) {
            doOnce = true;
            runnable.run();
            if (objectType == TYPE_ITEM) Respawn();
        }
        return 0;
    }

    private void Respawn() {
        positionX = spawnPoint;
        positionY = (float) Math.floor(Math.random() * (frameHeight - imageView.getHeight()));
        doOnce = false;
    }
}
