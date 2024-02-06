package com.emmanuel.android2dgamedev;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {

    private double positionx;
    private double positiony;
    private double radius;
    private Paint paint;

    public Player(Context context, double positionx, double positiony, double radius) {
        this.positionx = positionx;
        this.positiony = positiony;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);

    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionx, (float) positiony, (float) radius, paint);
    }

    public void update() {
    }

    public void setPosition(double positionx, double positiony) {
        this.positionx = positionx;
        this.positiony = positiony;

    }
}
