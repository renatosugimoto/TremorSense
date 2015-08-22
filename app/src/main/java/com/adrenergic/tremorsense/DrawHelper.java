package com.adrenergic.tremorsense;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class DrawHelper {
    private Paint paint;
    private Path path;

    public DrawHelper(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        path = new Path();
    }

    public void addPoint(float x, float y, boolean reset){
        if(reset){
            path.reset();
            path.moveTo(x, y);
        } else {
            path.lineTo(x, y);
        }
    }

    public Path getPath(){
        return path;
    }

    public Paint getPaint(){
        return paint;
    }
}
