package com.issakass.sudoku.engine.logic;

import com.issakass.sudoku.utils.Helpers;

import static com.issakass.sudoku.utils.Constants.BOX;
import static com.issakass.sudoku.utils.Constants.GRID;

public class Solver {
    // check if a number is present in a row
    public static boolean IsNumberInRow(int[][] board, int number, int row, int column) {
        int i;
        for (i = 0; i < GRID; i++) {
            if (i == column) continue;
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    // check if a number is present in a column
    public static boolean IsNumberInColumn(int[][] board, int number, int row, int column) {
        int i;
        for (i = 0; i < GRID; i++) {
            if (i == row) continue;
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    // check if a number is present in a box
    public static boolean IsNumberInBox(int[][] board, int number, int row, int column) {
        int boxStartRow = row - row % BOX;
        int boxStartColumn = column - column % BOX;
        int r, c;

        for (r = boxStartRow; r < boxStartRow + BOX; r++) {
            for (c = boxStartColumn; c < boxStartColumn + BOX; c++) {
                if (row == r && column == c) continue;
                if (board[r][c] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // check if the placement of a number is valid
    public static boolean IsNumberValid(int[][] board, int number, int row, int column) {
        return !IsNumberInRow(board, number, row, column) &&
                !IsNumberInColumn(board, number, row, column) &&
                !IsNumberInBox(board, number, row, column) &&
                board[row][column] != 0;
    }

    // solve the sudoku (backtrack algorithm)
    public static boolean SolveSudoku(int[][] board) {
        int row, column, numberToTry;
        for (row = 0; row < GRID; row++) {
            for (column = 0; column < GRID; column++) {
                if (board[row][column] == 0) {
                    for (numberToTry = 1; numberToTry <= GRID; numberToTry++) {
                        if (IsNumberValid(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (SolveSudoku(board)) return true;
                            else board[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // check if the sudoku is solved (int[][])
    private static boolean IsSolved(int[][] board) {
        int row, column;
        for (row = 0; row < GRID; row++) {
            for (column = 0; column < GRID; column++) {
                if (!IsNumberValid(board, board[row][column], row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    // check if the sudoku is solved (sudokuSquare[][])
    public static boolean IsSolved(SudokuSquare[][] sudokuPuzzle) {
        int[][] board = Helpers.CopySquareValues(sudokuPuzzle);
        return IsSolved(board);
    }
}
