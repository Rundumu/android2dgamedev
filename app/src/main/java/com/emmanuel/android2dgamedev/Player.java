package com.emmanuel.android2dgamedev;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {

    private static final double SPEED_PIXELS_PER_SECOND = 400;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double positionx;
    private double positiony;
    private double radius;
    private Paint paint;

    private double velocityX;
    private double velocityY;



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

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        positionx += velocityX;
        positiony += velocityY;

    }

    public void setPosition(double positionx, double positiony) {
        this.positionx = positionx;
        this.positiony = positiony;

    }
}
