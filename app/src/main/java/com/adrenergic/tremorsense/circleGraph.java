package com.adrenergic.tremorsense;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Jackson on 15/07/2015.
 */

public class circleGraph extends View {
    DrawHelper helperShape;
    int squareDim = 1000000000;

    public circleGraph(Context context){
        super(context);
        initCircleGraph();
    }

    public circleGraph(Context context, AttributeSet attrs){
        super(context, attrs);
        initCircleGraph();
    }

    public void initCircleGraph(){
        helperShape = new DrawHelper();
    }

    public void appendToPath(double x, double y, boolean reset){
        float fX = (float)x;
        float fY = (float)y;
        helperShape.addPoint(fX, fY, reset);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawPath(helperShape.getPath(), helperShape.getPaint());
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int h = this.getMeasuredHeight();
        int w = this.getMeasuredWidth();
        int curSquareDim = Math.min(w,h);

        if(curSquareDim < squareDim)
        {
            squareDim = curSquareDim;
        }

        setMeasuredDimension(squareDim, squareDim);
    }
}
