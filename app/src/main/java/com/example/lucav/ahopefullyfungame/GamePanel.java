package com.example.lucav.ahopefullyfungame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by lucav on 06.04.2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    // Class we will construct
    private MainThread thread;

    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    // Constructor
    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        player = new RectPlayer(new Rect(100,100,200,200), Color.rgb(255, 0, 0));
        playerPoint = new Point(150,150);

        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // rerun MainThread
        thread = new MainThread(getHolder(), this);

        // makes game loop start running
        thread.setRunning(true);
        // method inside thread class which will be extended
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //stop current thread
        boolean retry = true;
        while(true) {
            try {
                //stops game
                thread.setRunning(false);
                thread.join();
            }catch(Exception e) {e. printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int)event.getX(), (int)event.getY());
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        player.update(playerPoint);
        obstacleManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
        obstacleManager.draw(canvas);
    }
}
