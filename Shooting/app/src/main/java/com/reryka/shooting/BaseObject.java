package com.reryka.shooting;

import android.graphics.Canvas;

public abstract class BaseObject {
    static final int STATE_NORMAL = -1;
    static final int STATE_DESTROYED = 0;

    int state = STATE_NORMAL;

    enum Type {
        Droid,
        Bullet,
        Missile,
        City,
    }

    float xPosition;
    float yPosition;

    public abstract Type getType();

    public abstract void draw(Canvas canvas);

    public boolean isAvailable(int width, int height) {
        if (yPosition < 0 || xPosition < 0 || yPosition > height || xPosition > width) {
            return false;
        }

        if (state == STATE_DESTROYED) {
            return false;
        }

        return true;
    }

    public abstract void move();

    public abstract boolean isHit(BaseObject object);

    public void hit() {
        state = STATE_DESTROYED;
    }

    static double calcDistance(BaseObject object1, BaseObject object2) {
        float distX = object1.xPosition - object2.xPosition;
        float distY = object1.yPosition - object2.yPosition;
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }
}
