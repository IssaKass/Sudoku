package com.issakass.sudoku.engine.logic;

import java.util.Random;

import static com.issakass.sudoku.utils.Constants.GRID;

public class Generator {
    private static final Random random = new Random();
    private static final int[][] board = new int[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 1, 5, 6, 4, 8, 9, 7},
            {5, 6, 4, 8, 9, 7, 2, 3, 1},
            {8, 9, 7, 2, 3, 1, 5, 6, 4},
            {3, 1, 2, 6, 4, 5, 9, 7, 8},
            {6, 4, 5, 9, 7, 8, 3, 1, 2},
            {9, 7, 8, 3, 1, 2, 6, 4, 5}
    };

    private static void shuffleNumbers() {
        int i, ranNum;
        for (i = 0; i < GRID; i++) {
            ranNum = random.nextInt(GRID);
            swapNumbers(i, ranNum);
        }
    }

    private static void swapNumbers(int n1, int n2) {
        int row, col;
        for (row = 0; row < GRID; row++) {
            for (col = 0; col < GRID; col++) {
                if (board[row][col] == n1) board[row][col] = n2;
                else if (board[row][col] == n2) board[row][col] = n1;
            }
        }
    }

    private static void shuffleRows() {
        int i, blockNumber, ranNum;
        for (i = 0; i < GRID; i++) {
            ranNum = random.nextInt(3);
            blockNumber = i / 3;
            swapRows(i, blockNumber * 3 + ranNum);
        }
    }

    private static void swapRows(int r1, int r2) {
        int[] row = board[r1];
        board[r1] = board[r2];
        board[r2] = row;
    }

    private static void shuffleCols() {
        int i, blockNumber, ranNum;
        for (i = 0; i < GRID; i++) {
            ranNum = random.nextInt(3);
            blockNumber = i / 3;
            swapCols(i, blockNumber * 3 + ranNum);
        }
    }

    private static void swapCols(int c1, int c2) {
        int i, colVal;
        for (i = 0; i < GRID; i++) {
            colVal = board[i][c1];
            board[i][c1] = board[i][c2];
            board[i][c2] = colVal;
        }
    }

    private static void shuffle3X3Rows() {
        int i, ranNum;
        for (i = 0; i < 3; i++) {
            ranNum = random.nextInt(3);
            swap3X3Rows(i, ranNum);
        }
    }

    private static void swap3X3Rows(int r1, int r2) {
        int i;
        for (i = 0; i < 3; i++) {
            swapRows(r1 * 3 + i, r2 * 3 + i);
        }
    }

    private static void shuffle3X3Cols() {
        int i, ranNum;
        for (i = 0; i < 3; i++) {
            ranNum = random.nextInt(3);
            swap3X3Cols(i, ranNum);
        }
    }

    private static void swap3X3Cols(int c1, int c2) {
        int i;
        for (i = 0; i < 3; i++) {
            swapCols(c1 * 3 + i, c2 * 3 + i);
        }
    }

    private static int[][] shuffleBoard() {
        int[][] res = board;

        do {
            shuffleCols();
            shuffleRows();
            shuffleNumbers();
            shuffle3X3Rows();
            shuffle3X3Rows();
        } while (!Solver.SolveSudoku(res));

        return res;
    }

    public static int[][] GenerateBoard() {
        return shuffleBoard();
    }

    private static SudokuSquare[][] CreateSudoku(int[][] board) {
        SudokuSquare[][] sudoku = new SudokuSquare[GRID][GRID];
        int row, col;
        for (row = 0; row < GRID; row++) {
            for (col = 0; col < GRID; col++) {
                sudoku[row][col] = new SudokuSquare(board[row][col]);
            }
        }
        return sudoku;
    }

    private static void ClearSquares(SudokuSquare[][] sudoku) {
        int row, col;
        for (row = 0; row < GRID; row++) {
            for (col = 0; col < GRID; col++) {
                if (sudoku[row][col].fixed) continue;
                sudoku[row][col].value = 0;
            }
        }
    }

    public static SudokuSquare[][] GenerateSudoku(Difficulty difficulty) {
        SudokuSquare[][] sudoku = CreateSudoku(shuffleBoard());
        int fixedNumbersCount = difficulty.startNumbers;
        int row, col;

        while (fixedNumbersCount > 0) {
            do {
                row = random.nextInt(GRID);
                col = random.nextInt(GRID);
            } while (sudoku[row][col].fixed);

            sudoku[row][col].fixed = true;
            fixedNumbersCount--;
        }
        ClearSquares(sudoku);
        return sudoku;
    }
}
