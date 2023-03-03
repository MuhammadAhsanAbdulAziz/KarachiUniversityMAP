package com.example.karachiuniversitymap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

class Line {
    float startX, startY, stopX, stopY;
    public Line(float startX, float startY, float stopX, float stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }
}

public class DrawView extends View {
    Paint paint = new Paint();
    ArrayList<Line> lines = new ArrayList<Line>();
    ArrayList<Float> xPts = new ArrayList<>();
    ArrayList<Float> yPts = new ArrayList<>();

    public DrawView(Context context, ArrayList<Float> xPts, ArrayList<Float> yPts) {
        super(context);
        this.xPts = xPts;
        this.yPts = yPts;
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.BEVEL);

        for (int i = 0;i < xPts.size()-1;i++)
        {
            lines.add(new Line(xPts.get(i) , yPts.get(i) , xPts.get(i + 1)  , yPts.get(i + 1) ));
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        for (Line l : lines) {
            canvas.drawLine(l.startX, l.startY, l.stopX, l.stopY, paint);
        }
    }




}