package com.example.molsapplication;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * LatinSquare class that is a custom view that produces an N x N grid to work as a game board.
 * @author Leon Mills 979610
 *
 * This class references some of the content produced in the video tutorial series
 * "Make a Sudoku Solver App! | How to make a Sudoku solver in Android Studio" by Practical Coding
 * on youtube, produced in October 2020, used in Janruary 2021.
 * URL : https://www.youtube.com/watch?v=lYjSl_ou05Q
 *
 * To be clear most of the content here can be found on the android developers webiste and is
 * not unique to this tutorial(https://developer.android.com/training/custom-views/custom-drawing)
 * I did not watch beyond creation of the grid, any other similarities are coincidence.
 *
 * The content has been adapted and made into my own, with only calculations for drawing
 * grid lines remaining the same. There are substantial differences as his grid targets a sudoku
 * solver whilst this represents Latin squares of order N.
 *
 */


public class LatinSquare extends View {
    private int lineColour;
    private int selectColour;
    private final Paint lcPaint = new Paint();
    private final Paint lcPaint2 = new Paint();
    private final Paint scPaint = new Paint();

    private int size;
    private int parameters;
    private int col = -1;
    private int row = -1;
    private int [][] latinSquareValues;

    public LatinSquare(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public int[][] getLatinSquareValues() {
        return latinSquareValues;
    }

    public int[] getColAndRow(){
        return new int[]{col,row};
    }

    /**
     * Method for obtaining the specific attributes of the grid.
     * @param newSize represents the size of the grid (N)
     * @param col represents colour that will be used for lines of the grid
     * @param col1 represents colour that will be used to highlight cell
     * @param array represents the values that will be displayed in the grid.
     */
    public void GridAttr (int newSize, int col, int col1, int[][] array){
        size = newSize;
        lineColour = col;
        selectColour = col1;
        latinSquareValues = array;
    }

    /**
     * Method that updates the values in the grid if a cell in the grid is selected.
     * @param value represents the value that will be inserted into a cell.
     * @return whether or not the array has been updated.
     */
    public boolean updateArray(int value){
        if ((col != -1) && (row != -1)){
            latinSquareValues[row-1][col-1] = value;
            col = -1;
            row = -1;
            return true;
        }
        return false;
    }

    /**
     * Methods that creates all paint brushes for canvas drawing methods using
     * the colours from GridAttr
     * @param size will be used to determine the size of the paint used for text.
     */
    private void paint(int size){
        lcPaint.setStyle(Paint.Style.STROKE);
        lcPaint.setStrokeWidth(9);
        lcPaint.setColor(lineColour);
        lcPaint2.setStyle(Paint.Style.FILL);
        lcPaint2.setTextSize(80 - (size * 2));
        lcPaint2.setColor(lineColour);
        scPaint.setStyle(Paint.Style.FILL);
        scPaint.setColor(selectColour);
    }

    /**
     * Overriding the onMeasure method in order to set the measurements of the custom view
     * @param width represents the width of the custom view
     * @param height represents the Height of the custom view
     */
    @Override
    protected void onMeasure(int width, int height){
        int measure;
        // Portrait
        if (width < height){
            measure = (int)Math.ceil(MeasureSpec.getSize(width)/1.4);
        }
        // Landscape
        else {
            measure = (int)Math.ceil(MeasureSpec.getSize(height)/1.4);
        }

        setMeasuredDimension(measure,measure);
        parameters = measure/size;
    }

    /**
     * Overriding the onDraw method in order to visualize the grid and the numbers inside the grid.
     * As well as the highlight colour on a selected cell
     * @param canvas represents the area which the contents will be drawn upon.
     */
     @Override
     protected void onDraw(Canvas canvas){
        paint(size);
        // Highlighted cell colour
        if (col != -1 && row != -1){
            canvas.drawRect((col-1)* parameters, (row-1)* parameters,
                    col* parameters, row* parameters, scPaint);
        }
        invalidate();
        // Draw grid
        canvas.drawRect(0, 0, getWidth(),getHeight(), lcPaint);
        for(int lineStop = 0; lineStop<size; lineStop++){
            canvas.drawLine(parameters *lineStop, 0, parameters *lineStop, getHeight(), lcPaint);
            canvas.drawLine(0, parameters *lineStop, getWidth(), parameters *lineStop, lcPaint);
        }

        // Draw values
        for(int row = 0; row <size; row++){
            for(int col = 0; col <size; col++){
                int x = (((col+1)*parameters) - ((parameters/5)*3));
                int y = (((row+1)*parameters) - ((parameters/5)*2));
                String number = Integer.toString(latinSquareValues[row][col]);
                if (latinSquareValues[row][col] != 0){
                    canvas.drawText(number, x , y, lcPaint2);
                }
            }
        }
     }

    /**
     * Override onTouchEvent in order to obtain cell coordinates
     * @param event represents the action such as button clicks
     * @return will produce true value if mouse is clicked on the canvas.
     */
     @Override
     public boolean onTouchEvent (MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            col = (int)Math.ceil(event.getX()/ parameters);
            row = (int)Math.ceil(event.getY()/ parameters);
           return true;
        }
        return false;
     }

}


