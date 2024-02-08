package com.emmanuel.android2dgamedev;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {

    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private boolean isPressed;
    private int outerCircleRadius;
    private int innerCircleRadius;

    private int outerPositionCircleX;
    private int outerPositionCircleY;

    private int innerPositionCircleX;
    private int innerPositionCircleY;
    private double joystickCenterToTouchDistance;
    private double actuatorX;

    private double actuatorY;


    public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius){
        // position of circles
        outerPositionCircleX = centerPositionX;
        outerPositionCircleY = centerPositionY;
        innerPositionCircleX = centerPositionX;
        innerPositionCircleY = centerPositionY;

        // radius of circles
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;
        
        //Paint joystick
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.BLUE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    public void draw(Canvas canvas) {
        // draw outer circle
        canvas.drawCircle(outerPositionCircleX, outerPositionCircleY, outerCircleRadius, outerCirclePaint);

        // draw inner circle
        canvas.drawCircle(innerPositionCircleX, innerPositionCircleY, innerCircleRadius, innerCirclePaint);
    }

    public void update() {
        updateInnerCirclePosition();

    }

    private void updateInnerCirclePosition() {
        innerPositionCircleX = (int) (outerPositionCircleX + actuatorX*outerCircleRadius);
        innerPositionCircleY = (int) (outerPositionCircleY + actuatorY*outerCircleRadius);

    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {
        joystickCenterToTouchDistance = Math.sqrt(
                Math.pow(outerPositionCircleX - touchPositionX, 2) +
                        Math.pow(outerPositionCircleY - touchPositionY, 2)
        );
        return joystickCenterToTouchDistance < outerCircleRadius;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed(){
        return isPressed;
    }

    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltax = touchPositionX - outerPositionCircleX;
        double deltay = touchPositionY - outerPositionCircleY;
        double deltaDistance = Math.sqrt(
                Math.pow(deltax, 2) +
                        Math.pow(deltay, 2)
        );

        if (deltaDistance < outerCircleRadius){
            actuatorX = deltax/outerCircleRadius;
            actuatorY = deltay/outerCircleRadius;
        } else {
            actuatorX = deltax/deltaDistance;
            actuatorY = deltay/deltaDistance;
        }


    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
