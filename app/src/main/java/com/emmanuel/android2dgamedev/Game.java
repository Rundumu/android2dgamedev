package com.emmanuel.android2dgamedev;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutInfoCompat;

import com.emmanuel.android2dgamedev.GameLoop;

// Game manages all the rendering and updates to the screen
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;

    private final Player player;
    public Game(Context context) {
        super(context);


        // Get surfaceholder and add callback
        SurfaceHolder surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);

        // initialize player
        player = new Player(getContext(), 500, 500, 30);

        gameLoop = new GameLoop(this, surfaceHolder);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        // Handle touch event actions
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.setPosition((double) event.getX(), (double) event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                player.setPosition((double) event.getX(), (double) event.getY());
                return true;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        player.draw(canvas);
    }

    // display updates per seconds
    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update(){
        player.update();

    }

}