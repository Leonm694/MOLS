package com.example.molsapplication;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackTest {
    int[][] a = {{1,0,3},{2,0,1}, {3,1,0}};
    int[][] b = {{1,0,3},{0,1,2}, {2,3,1}};
    int[][] c = {{1,0,3},{2,0,1}, {3,1,0}};
    int[][] d = {{0,5,3,1,4,2,6}, {4,1,6,2,5,3,0}, {3,0,1,4,6,5,2}, {2,6,5,0,3,4,1}, {6,4,2,5,0,1,3}, {5,2,0,3,1,6,4}, {1,3,4,6,2,0,5}};
    int[][] e = {{0,4,6,3,2,5,1}, {6,0,3,2,1,4,5}, {5,1,4,0,6,2,3}, {1,2,5,4,0,3,6}, {4,5,0,6,3,1,2}, {3,6,2,1,5,0,4}, {2,3,1,5,4,6,0}};
    int[][] f = {{0,5,3,1,4,2,6}, {4,1,6,2,5,3,0}, {3,0,1,4,6,5,2}, {2,6,5,0,3,4,1}, {6,4,2,5,0,1,3}, {5,2,0,3,1,6,4}, {1,3,4,6,2,0,5}};

    int[][] fourO = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
    int[][] fourO1 = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
    int[][] fiveO = {{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}};
    int[][] fiveO1 = {{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}};
    int[][] sixO = {{0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}};
    int[][] sixO1 = {{0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}};
    int[][] sevenO = {{0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}};
    int[][] sevenO1 = {{0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}, {0,0,0,0,0,0,0}};

    Backtrack backtrack = new Backtrack();



    @Test
    void backTrackfunctionality() {
        backtrack.btAlgorithm(a, b, 3);
        assertEquals(a[0][1], 2);
        assertEquals(a[1][1], 3);
        assertEquals(a[2][2], 2);
        assertFalse(backtrack.btAlgorithm(a, c, 3));
        assertTrue(backtrack.btAlgorithm(d, e, 7));
        assertFalse(backtrack.btAlgorithm(d, f, 7));
    }

    @Test
    void fourTest(){
        backtrack.btAlgorithm(fourO, fourO1, 4);
    }
    @Test
    void fiveTest(){
        backtrack.btAlgorithm(fiveO, fiveO1, 5);
    }

    @Test
    void sixTest(){
        backtrack.btAlgorithm(sixO, sixO1, 6);
    }

}