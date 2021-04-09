package com.example.molsapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

public class LatinSquare extends View {
    private int lineColour;
    private final Paint lcPaint = new Paint();
    private final Paint lcPaint2 = new Paint();
    private int selectColour;
    private final Paint scPaint = new Paint();
    private int size;
    private int parameters;
    int col = -1;
    int row = -1;
    int [][] latinSquareValues;

    public LatinSquare(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public int[][] getLatinSquareValues() {
        return latinSquareValues;
    }

    public void GridAttr (int newSize, int col, int col1, int[][] array){
        size = newSize;
        lineColour = col;
        selectColour = col1;
        latinSquareValues = array;
    }

    public boolean updateArray(int value){
        if ((col != -1) && (row != -1)){
            latinSquareValues[row-1][col-1] = value;
            col = -1;
            row = -1;
            return true;
        }
        return false;
    }

    private void paint(int size){
        lcPaint.setStyle(Paint.Style.STROKE);
        lcPaint.setStrokeWidth(10);
        lcPaint.setColor(lineColour);
        lcPaint2.setStyle(Paint.Style.FILL);
        lcPaint2.setTextSize(80 - (size * 2));
        lcPaint2.setColor(lineColour);
        scPaint.setStyle(Paint.Style.FILL);
        scPaint.setColor(selectColour);
    }

    @Override
    protected void onMeasure(int width, int height){
        int measure;
        if (width < height){
            measure = (int)Math.ceil(MeasureSpec.getSize(width)/1.4);
        }
        else {
            measure = (int)Math.ceil(MeasureSpec.getSize(height)/1.4);
        }

        setMeasuredDimension(measure,measure);
        parameters = measure/size;
    }
     @Override
     protected void onDraw(Canvas canvas){
        paint(size);
        if (col != -1 && row != -1){
            canvas.drawRect((col-1)* parameters, (row-1)* parameters,
                    col* parameters, row* parameters, scPaint);
        }
        invalidate();
        canvas.drawRect(0, 0, getWidth(),getHeight(), lcPaint);
        for(int lineStop = 0; lineStop<size+1; lineStop++){
            canvas.drawLine(parameters *lineStop, 0, parameters *lineStop, getHeight(), lcPaint);
            canvas.drawLine(0, parameters *lineStop, getWidth(), parameters *lineStop, lcPaint);
        }

        for(int row = 0; row <size; row++){
            for(int col = 0; col <size; col++){
                int x = (((col+1)*parameters) - ((parameters/5)*3));
                int y = (((row+1)*parameters) - ((parameters/5)*2));
                String text = Integer.toString(latinSquareValues[row][col]);
                if (latinSquareValues[row][col] != 0){
                    canvas.drawText(text, x , y, lcPaint2);
                }
            }
        }
     }

     @Override
     public boolean onTouchEvent (MotionEvent event){
        boolean acceptedPos = false;
        double colValue = event.getX();
        double rowValue = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            col = (int)Math.ceil(colValue/ parameters);
            row = (int)Math.ceil(rowValue/ parameters);
            acceptedPos = true;
        }
        return acceptedPos;
     }

}


