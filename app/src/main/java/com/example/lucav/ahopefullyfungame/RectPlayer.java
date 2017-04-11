package com.example.lucav.ahopefullyfungame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by lucav on 10.04.2017.
 */

public class RectPlayer implements GameObject{

    private Rect rectangle;
    private int colour;

    public Rect getRectangle(){
        return rectangle;
    }

    public RectPlayer(Rect rectangle, int colour){
        this.rectangle = rectangle;
        this.colour = colour;
    }


    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(colour);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update(){

    }

    public void update(Point point){
        // set(left, top, right bottom)
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
