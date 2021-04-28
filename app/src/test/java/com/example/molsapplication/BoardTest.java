package com.example.molsapplication;

import android.app.Activity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board = new Board();
    int[][] a = {{1,2,3},{2,3,1}, {3,1,2}};
    int[][] a2 = {{2,3,4},{3,4,2}, {4,2,3}};
    int[][] a3 = {{1,2,0},{2,0,1}, {3,0,0}};
    int[][] b = {{1,2,3},{0,1,2}, {2,3,1}};
    int[][] c = {{1,2,3},{1,1,2}, {2,3,1}};
    int[]ls1CR = {2, 2};
    int[]ls2CR = {1, 2};


    @Test
    void checkLine() {
        assertTrue(board.checkLine(a, 3));
        assertFalse(board.checkLine(b, 3));
        assertFalse(board.checkLine(b, 3));
    }
    @Test
    void mutualOrthCheck() {
        assertTrue(board.mutualOrthCheck(a, b, 3));
        assertFalse(board.mutualOrthCheck(a, c, 3));
    }
    @Test
    void fillArray() {
        board.fillArray(a, 3);
        assertEquals(Arrays.deepToString(a2), Arrays.deepToString(a));
    }
    @Test
    void removePieces() {
        int count = 0;
        board.removePieces(a, 3);
        for (int i =0; i<3; i++){
            for (int j =0; j<3; j++){
                if (a[i][j] == 0){
                    count ++;
                }
            }
        }
        assertTrue(3 < count);
    }
    @Test
    void blankArray() {
        int count = 0;
        board.blankArray(a, 3);
        for (int i =0; i<3; i++){
            for (int j =0; j<3; j++){
                if (a[i][j] != 0){
                    count ++;
                }
            }
        }
        assertEquals(0, count);
    }
    @Test
    void bTrack() {
        int[] number = board.bTrack(a3, b, 3, ls1CR, ls2CR);
        assertEquals(number[0], 3);
        assertEquals(number[1], 3);
    }


}