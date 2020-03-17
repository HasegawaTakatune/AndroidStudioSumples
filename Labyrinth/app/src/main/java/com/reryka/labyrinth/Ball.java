package com.reryka.labyrinth;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Ball {

    private final Paint paint = new Paint();
    private final Bitmap ballBitmap;
    private final RectF rect;
    private final Rect srcRect;
    private final OnMoveListener listener;

    public interface OnMoveListener {
        int getCanMoveHorizontalDistance(RectF ballRect, int xOffset);

        int getCanMoveVerticalDistance(RectF ballRect, int yOffset);
    }

    public Ball(Bitmap bitmap, Map.Block startBlock, float scale, OnMoveListener listener) {
        this(bitmap, startBlock.rect.left, startBlock.rect.top, scale, listener);
    }

    public Ball(Bitmap bitmap, int left, int top, float scale, OnMoveListener listener) {
        ballBitmap = bitmap;
        float right = left + bitmap.getWidth() * scale;
        float bottom = top + bitmap.getHeight() * scale;
        rect = new RectF(left, top, right, bottom);
        srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        this.listener = listener;
    }

    void draw(Canvas canvas) {
        canvas.drawBitmap(ballBitmap, srcRect, rect, paint);
    }

    void move(float xOffset, float yOffset) {
        xOffset = listener.getCanMoveHorizontalDistance(rect, Math.round(xOffset));
        rect.offset(xOffset, 0);

        yOffset = listener.getCanMoveVerticalDistance(rect, Math.round(yOffset));
        rect.offset(0, yOffset);
    }
}
