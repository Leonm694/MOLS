package com.example.molsapplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Board class acts as the main activity for the application. This class utilises features from
 * LatinSquare, EulerSquareGen, PopUp and Backtrack in order to produce the main interface for the
 * game and allows everything to intertwine together.
 *
 * @author Leon Mills 979610
 */

public class Board extends Activity {
    private int score = 0;
    private int value = 0;
    private int buttonFive = 5;
    private int buttonSix = 6;
    private int buttonSeven = 7;
    private int buttonEight = 8;
    private int [][]a;
    private int [][]b;
    private int[][][] newES;
    private String mode = "";

    /**
     * OnCreate in this activity is huge due to the sheer amount of buttons that are active and
     * potentially change depending on the value of N.
     */
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        value = getIntent().getIntExtra("Value", 0);
        mode = getIntent().getStringExtra("Mode");
        int col = Color.parseColor("#000000");
        int col1 = Color.parseColor("#4FE4D8");
        int col2 = Color.parseColor("#BF82DF");

        // OneSolution mode ensures there is only one possible outcome to the MOLS
        if (value != 6 && mode.equals("OneSolution")){
            EulerSquareGen ES = new EulerSquareGen(value);
            newES = ES.generate();
            int[][][] alteredES = new int[2][][];
            for (int i = 0; i < 2; i++) {
                alteredES[i] = new int[value][];
                for (int j = 0; j < value; j++)
                    alteredES[i][j] = Arrays.copyOf(newES[i][j], value);
            }
            a = alteredES[0];
            b = alteredES[1];
            fillArray(newES[0], value);
            fillArray(newES[1], value);
            fillArray(a, value);
            fillArray(b, value);

            removePieces(a, value);
            removePieces(b, value);

            // AllSolution mode allows for a number of different solutions to be input.
        } else if (value == 6 || mode.equals("AllSolution")){
            a = new int[value][value];
            b = new int[value][value];
           blankArray(a, value);
           blankArray(b, value);
        }


       final LatinSquare ls1 = findViewById(R.id.latin_square1);
        final LatinSquare ls2 = findViewById(R.id.latin_square2);
        ls1.GridAttr(value, col, col1, a);
        ls2.GridAttr(value, col, col2, b);

        Button button1 = findViewById(R.id.materialButton1);
        Button button2 = findViewById(R.id.materialButton2);
        Button button3 = findViewById(R.id.materialButton3);
        Button button4 = findViewById(R.id.materialButton4);
        Button button5 = findViewById(R.id.materialButton5);
        Button button6 = findViewById(R.id.materialButton6);
        Button button7 = findViewById(R.id.materialButton7);
        Button button8 = findViewById(R.id.materialButton8);
        Button menu = findViewById(R.id.menu_button);
        Button solve = findViewById(R.id.solve);

        // Switch statement that alters the XML code depending on the value of N
        switch (value) {
            case 3:
                button4.setVisibility(View.GONE);
                button5.setText("1");
                buttonFive = 1;
                button6.setText("2");
                buttonSix = 2;
                button7.setText("3");
                buttonSeven = 3;
                button8.setVisibility(View.GONE);
                break;
            case 4:
                button5.setText("1");
                buttonFive = 1;
                button6.setText("2");
                buttonSix = 2;
                button7.setText("3");
                buttonSeven = 3;
                button8.setText("4");
                buttonEight = 4;
                break;
            case 5:
                button4.setVisibility(View.GONE);
                button7.setVisibility(View.GONE);
                button8.setVisibility(View.GONE);
                button5.setText("4");
                buttonFive = 4;
                button6.setText("5");
                buttonSix = 5;
                break;
            case 6:
                button4.setVisibility(View.GONE);
                button8.setVisibility(View.GONE);
                button5.setText("4");
                buttonFive = 4;
                button6.setText("5");
                buttonSix = 5;
                button7.setText("6");
                buttonSeven = 6;
            case 7:
                button8.setVisibility(View.GONE);
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Board.this, PopUp.class);
                intent.putExtra("Title", "Menu");
                intent.putExtra("Score", score);
                intent.putExtra("Value", value);
                intent.putExtra("Mode", mode);
                startActivity(intent);
            }
        });

        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value != 6) {
                    int x = 0;

                    for (int i = 0; i < value; i++) {
                        for (int j = 0; j < value; j++) {
                            if (a[i][j] == 0) {
                                x++;
                            }
                            if (b[i][j] == 0) {
                                x++;
                            }
                        }
                    }
                    // limits the solve function so that its only used when less than 48
                    // empty spaces exist, otherwise the function takes too long to solve.
                    if ((x < 45) || (x < 47 && value == 7)){
                        int[] ls1ColRow = ls1.getColAndRow();
                        int[] ls2ColRow = ls2.getColAndRow();
                        int[] number = bTrack(a, b, value, ls1ColRow, ls2ColRow);

                         boolean pass = ls1.updateArray(number[0]);
                        if (!pass) {
                             ls2.updateArray(number[1]);
                        }
                    score = score + 3;
                    isSolved(ls1, ls2, value);
                    } else{
                            Toast.makeText(Board.this, "Not enough spaces filled for solve, " +
                                    "please fill in more spaces first.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        System.out.println("Cant solve MOLS for 6");
                        }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean pass =  ls1.updateArray(1);
               if (!pass){
                   ls2.updateArray(1);
               }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pass =  ls1.updateArray(2);
                if (!pass){
                    ls2.updateArray(2);
                }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pass =  ls1.updateArray(3);
                if (!pass){
                    ls2.updateArray(3);
                }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pass =  ls1.updateArray(4);
                if (!pass){
                    ls2.updateArray(4);
                }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pass =  ls1.updateArray(buttonFive);
                if (!pass){
                    ls2.updateArray(buttonFive);
                }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pass =  ls1.updateArray(buttonSix);
                if (!pass){
                    ls2.updateArray(buttonSix);
                }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pass =  ls1.updateArray(buttonSeven);
                if (!pass){
                    ls2.updateArray(buttonSeven);
                }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pass =  ls1.updateArray(buttonEight);
                if (!pass){
                    ls2.updateArray(buttonEight);
                }
                score = score + 1;
                isSolved(ls1, ls2, value);
            }
        });
    }

    /**
     * This method is used every time a new value is input in the array to check if the MOLS
     * has been complete.
     * @param latinSquareOne represents a Latin square (Custom View)
     * @param latinSquareTwo represents a Latin square (Custom View)
     * @param value represents N
     */
    public void isSolved (LatinSquare latinSquareOne, LatinSquare latinSquareTwo, int value){

        int[][] a = latinSquareOne.getLatinSquareValues();
        int[][] b = latinSquareTwo.getLatinSquareValues();
        boolean check = false;

        // OneSolution can compare current board to the Euler square produced by the generator
        if (mode.equals("OneSolution")){
            if ((Arrays.deepToString(a).equals(Arrays.deepToString(newES[0]))) &&
                    (Arrays.deepToString(b).equals(Arrays.deepToString(newES[1])))){
                check = true;
            }
        }
        // AllSolution must run through the line checks and MOLS check.
        else if (mode.equals("AllSolution")){
            boolean lineCheckA = checkLine(a, value);
            boolean lineCheckB = checkLine(b, value);
            boolean mutualCheck = mutualOrthCheck(a, b, value);
            if((lineCheckA) && (lineCheckB) && (mutualCheck)){
                check = true;
            }
        }

        if(check){
            System.out.println("Game successful" + score);
            Intent intent = new Intent(Board.this, PopUp.class);
            intent.putExtra("Title", "Congratulations! You won");
            intent.putExtra("Score", score);
            intent.putExtra("Value", value);
            intent.putExtra("Mode", mode);
            startActivity(intent);

        }
        else {
            System.out.println("Keep going" + score);
        }

    }

    /**
     * This method checks that a given row or column passes the requirements of accumulating to
     * a value of the boundary. for example in a 3X3 grid each line must = 6 (1+2+3)
     *
     * @param array represents the values found in the Latin square
     * @param size represents the order N
     * @return true if the sum of the row/col is equal to the boundary.
     */
    public boolean checkLine (int[][] array, int size){
        int columnMax;
        int rowMax;
        int boundary = 0;

        for (int i = 0; i <= size; i++){
            boundary = boundary + i;
        }
        for (int i = 0; i < size; i++){
            columnMax = 0;
            rowMax = 0;
            for (int j = 0; j < size; j++){
                if (array[i][j] == 0 || array[j][i] == 0){
                    return false;
                } else {
                    columnMax = columnMax + array[i][j];
                    rowMax = rowMax + array[j][i];
                }
            }
            if ((columnMax != boundary) || (rowMax != boundary)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method that runs through all positional pairs of two 2D arrays, adding them to an arraylist
     * to identify if all pairs are unique.
     * @param arrayOne represents the values found in the Latin square
     * @param arrayTwo represents the values found in the Latin square
     * @param size represents the order N
     * @return true if all pairs are unique.
     */
    public boolean mutualOrthCheck (int[][] arrayOne, int[][] arrayTwo, int size){
        ArrayList<Object[]> pairs = new ArrayList<>();
       for (int i = 0; i < size; i++){
           for (int j = 0; j < size; j++){
               int positionOne = arrayOne[i][j];
               int positionTwo = arrayTwo[i][j];
               Object[] a ={positionOne,positionTwo};
               if(positionOne == 0 || positionTwo == 0){
                   return true;
               }

               for (Object [] element : pairs){
                   if (Arrays.deepToString(element).equals(Arrays.deepToString(a))){
                       return false;
                   }
               }
               pairs.add(a);

           }
       }
        return true;
    }

    /**
     * Method that adds 1 to each value of the EulerSquareGen Euler square.
     * @param array represents the values found in the Latin square
     * @param value represents the order N
     */
    public void fillArray (int[][] array, int value){
        for (int i = 0; i < value; i++ ){
            for (int j = 0; j < value; j++ ){
               int n = array[i][j];
               array[i][j] = n+1;
            }
        }
    }

    /**
     * Method to remove a number of positions from the EulerSquareGen Euler square so that
     * a user can solve a puzzle but there is only one solution.
     * @param array represents the values found in the Latin square
     * @param value represents the order N
     */
    public void removePieces(int[][] array, int value){
        int numberOfBlanks = (int) Math.floor((double)value/2);
        Random random = new Random();

        for (int j = 0; j <2; j++) {
            int r = random.nextInt(value);
            int r2 = random.nextInt(value);
            if (array[r][r2] != 0) {
                array[r][r2] = 0;

            }
        }
        for (int i = 0; i < value; i++) {
            for (int j = 0; j <numberOfBlanks;) {
                int r = random.nextInt(value);
                if (array[i][r] != 0) {
                    array[i][r] = 0;
                    j++;
                }
            }
        }

    }

    /**
     * Method to produce a blank array of 0s
     * @param array represents the values found in the Latin square
     * @param value represents the order N
     */
    public void blankArray (int[][] array, int value){
        for (int i = 0; i < value; i++ ){
            for (int j = 0; j < value; j++ ){
                array[i][j] = 0;
            }
        }
    }


    /**
     * Method used to produce a solution for a given position on the latin squares. Makes use of the
     * Backtrack class to produce a solution and gets the values of the selected position from
     * the Latin square class. Then returns the value from the solved MOLS for the specific position.
     * @param array represents the values found in the Latin square
     * @param arrayTwo represents the values found in the Latin square
     * @param value represents the order N
     * @param ls1RC Represents the position selected in Latin square 1
     * @param ls2RC Represents the position selected in Latin square 2
     * @returns values that are to be placed in either ls1 or Ls2
     */
    public int[] bTrack (int[][] array, int[][]arrayTwo, int value, int[] ls1RC, int[] ls2RC){
        int x = 0;
        int y = 0;
        int[][] a = new int[value][value];
        int[][] b = new int[value][value];
        for (int j = 0; j < value; j++){
            a[j] = Arrays.copyOf(array[j], value);
        }
        for (int j = 0; j < value; j++) {
            b[j] = Arrays.copyOf(arrayTwo[j], value);
        }
        Backtrack backtrack = new Backtrack();

        boolean fact = backtrack.btAlgorithm(a, b, value);
        if (!fact){
            Toast.makeText(Board.this, "No possible solution, " +
                    "please change existing values", Toast.LENGTH_SHORT).show();
        }
        //System.out.println("a " + Arrays.deepToString(a));
        //System.out.println("b " + Arrays.deepToString(b));

        if(ls1RC[0] != -1 || ls1RC[1] != -1){
            //System.out.println("wooo" + ls1RC[0] + " and" + ls1RC[1]);
            x = a[ls1RC[1]-1][ls1RC[0]-1];
        }

        if(ls2RC[0] != -1 || ls2RC[1] != -1){
            //System.out.println("wooo" + ls2RC[0] + " and" + ls2RC[1]);
            y = b[ls2RC[1]-1][ls2RC[0]-1];
        }
        return new int[]{x,y};
    }

}
