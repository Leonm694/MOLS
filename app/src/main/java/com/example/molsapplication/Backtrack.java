package com.example.molsapplication;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Backtrack is a class that carries out a recursive call in order to generate a Mutually
 * Orthogonal Latin Square through backtracking.
 * @author Leon Mills 979610
 *
 * slight reference to techwithtims python Sudoku Solver backtracking guide that was
 * created in 2019.
 * https://www.techwithtim.net/tutorials/python-programming/sudoku-solver-backtracking/
 *
 * Next space is java equivalent of his findempty method.
 * btAlgorithm utilises the theory of the recursion, however the test cases are unique to my work.
 */

public class Backtrack {
    /**
     * This method takes positions from positionList and find the values of that position in 2 given
     * arrays in order to produce a pair of values. These values are then added to a hashmap and if
     * there is no repeated pairs the check will be complete stating that the latin squares are
     * mutual orthogonal.
     * @param arrayOne represents values in a Latin square
     * @param arrayTwo represents values in a Latin square
     * @param positionList represents the positions relevant to the search
     * @return True if there is no repeated pairs.
     */
    private boolean mutualOrthCheck2 (int[][] arrayOne, int[][] arrayTwo, ArrayList<int[]> positionList){
        Map<Integer, Integer> p2 = new LinkedHashMap<>();
        for (int i = 0; i < positionList.size(); i++){
            int[] testPost = positionList.get(i);
            int positionOne = arrayOne[testPost[0]][testPost[1]];
            int positionTwo = arrayTwo[testPost[0]][testPost[1]];
            if (p2.containsKey(positionOne) && p2.containsValue(positionTwo)){
                return false;
            }
            p2.put(positionOne, positionTwo);
        }
        return true;
    }

    /**
     * Find next available empty space in a 2D array
     * @param array represents values in a Latin square
     * @param value represents the order of the Latin square
     * @return in array contains position of empty space
     */
    private int[] nextSpace (int[][] array, int value){
        for (int i = 0; i < value; i++){
            for(int j = 0; j < value; j++){
                if (array[i][j] == 0){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * Backtracking algorithm that is recursive. For each empty space found, it will attempt to
     * fill in the blank space with a number from 1 to value, running checks to see if that number
     * is able to fit in that space. Each time a number fails the code will try a different applicable
     * value, if all fail it goes back a step and tries a different number. This persists until
     * either all empty spaces are filled with passing values or it fails.
     *
     * @param array represents values in a Latin square
     * @param arrayTwo represents values in a Latin square
     * @param value represents the order of the Latin square
     * @return True if all empty places are correctly filled.
     */

    public boolean btAlgorithm (int[][] array, int[][]arrayTwo, int value){
        int[] ls1Pos = nextSpace(array, value);
        int[] ls2Pos = nextSpace(arrayTwo, value);
        int row;
        int col;

        // If no empty places are found in square one we move onto square 2.
        if(ls1Pos == null){
            // If no empty places are found in square two then we have created a pair of MOLS
            if(ls2Pos == null){
                return true;
            }
            else {
                row = ls2Pos[0];
                col = ls2Pos[1];
            }
        } else {
            row = ls1Pos[0];
            col = ls1Pos[1];
        }

        for(int i = 0; i < value; i++){
            boolean line;
            boolean mols;

            // Latin square one checks
            if (ls1Pos != null){
                ArrayList<int[]> pairs = new ArrayList<>();
                line = true;
                // Checking if the value is found in corresponding row or column
                for(int j =0; j<value; j++){
                    if ((array[row][j] == i + 1 && j != col) ||
                            (array[j][col] == i + 1 && j != row)) {
                        line = false;
                        break;
                    }
                     // find all other values in the square that have the same value, then
                     // add to an array list and run MOLS check on that list.
                    for (int k=0; k<value; k++){
                        if (((array[j][k] == i +1) && (array[j][k] != 0)
                                && (arrayTwo[j][k] != 0)) || (j == row && k == col)){

                            int[] a ={j,k};
                            pairs.add(a);

                        }
                    }
                }
                array[row][col] = i+1;
                mols = mutualOrthCheck2(array, arrayTwo, pairs);
                array[row][col] = 0;
                if(mols && line){
                    array[row][col] = i+1;
                    if(btAlgorithm(array, arrayTwo, value)){
                        return true;
                    }
                    array[row][col] = 0;
                }
            }
            // Latin square two checks
            else {
                ArrayList<int[]> pairs = new ArrayList<>();
                line = true;
                // Checking if the value is found in corresponding row or column
                for(int j =0; j<value; j++){
                    if ((arrayTwo[row][j] == i + 1 && j != col) ||
                            (arrayTwo[j][col] == i + 1 && j != row)) {
                        line = false;
                        break;
                    }
                    // find all other values in the square that have the same value, then
                    // add to an array list and run MOLS check on that list.
                    for (int k=0; k<value; k++){
                        if (((arrayTwo[j][k] == i +1) && (array[j][k] != 0)
                                && (arrayTwo[j][k] != 0)) || (j == row && k == col)){
                            int[] a ={j,k};
                            pairs.add(a);
                        }
                    }
                }
                arrayTwo[row][col] = i+1;
                mols = mutualOrthCheck2(array, arrayTwo, pairs);
                arrayTwo[row][col] = 0;

                if(mols && line){
                    arrayTwo[row][col] = i+1;
                    if(btAlgorithm(array, arrayTwo, value)){
                        return true;
                    }
                    arrayTwo[row][col] = 0;
                }
            }
        }

        return false;
    }
}
