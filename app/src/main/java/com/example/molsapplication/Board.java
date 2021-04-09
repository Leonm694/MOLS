package com.example.molsapplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board extends Activity {
    int score = 0;
    int value = 0;
    int buttonFive = 5;
    int buttonSix = 6;
    int buttonSeven = 7;
    int buttonEight = 8;
    int [][]a;
    int [][]b;
    int[][][] newES;
    int[][][] alteredES;
    String mode = "";

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

        if (value != 6 && mode.equals("OneSolution")){
            EulerSquareGen ES = new EulerSquareGen(value);
            newES = ES.generate();
            alteredES = new int[2][][];
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


    public void isSolved (LatinSquare latinSquareOne, LatinSquare latinSquareTwo, int size){

        int[][] a = latinSquareOne.getLatinSquareValues();
        int[][] b = latinSquareTwo.getLatinSquareValues();
        boolean check = false;

        if (mode.equals("OneSolution")){
            if ((Arrays.deepToString(a).equals(Arrays.deepToString(newES[0]))) &&
                    (Arrays.deepToString(b).equals(Arrays.deepToString(newES[1])))){
                check = true;
            }
        }
        else if (mode.equals("AllSolution")){
            boolean lineCheckA = checkLine(a, size);
            boolean lineCheckB = checkLine(b, size);
            boolean mutualCheck = mutualOrthCheck(a, b, size);
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

    public boolean checkLine (int[][] array, int size){
        int columnMax;
        int rowMax = 0;
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

    public boolean mutualOrthCheck (int[][] arrayOne, int[][] arrayTwo, int size){
        ArrayList<Object[]> pairs = new ArrayList<>();
       for (int i = 0; i < size; i++){
           for (int j = 0; j < size; j++){
               int positionOne = arrayOne[i][j];
               int positionTwo = arrayTwo[i][j];
               Object[] a ={positionOne,positionTwo};

               for (Object [] element : pairs){
                   System.out.println("element = " + Arrays.deepToString(element));
                   System.out.println("a = " + Arrays.deepToString(a));

                   if (Arrays.deepToString(element).equals(Arrays.deepToString(a))){
                       return false;
                   }
               }
               pairs.add(a);
           }
       }
        return true;
    }

    public void fillArray (int[][] array, int value){
        for (int i = 0; i < value; i++ ){
            for (int j = 0; j < value; j++ ){
               int n = array[i][j];
               array[i][j] = n+1;
            }
        }
    }

    public void removePieces(int[][] array, int value){
        int numberOfBlanks = (int) Math.floor((((double) value * (double) value)/2)/(double)value);
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

    public void blankArray (int[][] array, int value){
        for (int i = 0; i < value; i++ ){
            for (int j = 0; j < value; j++ ){
                array[i][j] = 0;
            }
        }
    }


}
