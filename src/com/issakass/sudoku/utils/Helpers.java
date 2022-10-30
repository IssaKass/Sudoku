package com.issakass.sudoku.utils;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.engine.logic.SudokuSquare;
import com.issakass.sudoku.engine.logic.Solver;
import com.issakass.sudoku.engine.state.StateID;
import com.issakass.sudoku.engine.state.states.PlayState;

import javax.swing.*;

import static com.issakass.sudoku.utils.Constants.GRID;

public class Helpers {
    public static boolean IsCoordInsideBounds(int row, int col) {
        return (row >= 0 && row < GRID) && (col >= 0 && col < GRID);
    }

    public static int[][] CopySquareValues(SudokuSquare[][] sudokuPuzzle) {
        int[][] res = new int[GRID][GRID];
        int row, col;
        for (row = 0; row < GRID; row++) {
            for (col = 0; col < GRID; col++) {
                res[row][col] = sudokuPuzzle[row][col].value;
            }
        }
        return res;
    }

    public static void PrintSudoku(int[][] sudokuPuzzle) {
        int row, col;

        System.out.print("------------------------------\n");
        for (row = 0; row < GRID; row++) {
            System.out.print("|");
            for (col = 0; col < GRID; col++) {
                System.out.print(" " + sudokuPuzzle[row][col] + " ");

                if ((col + 1) % 3 == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if ((row + 1) % 3 == 0) {
                System.out.print("------------------------------\n");
            }
        }
    }

    public static void PrintSolution(int[][] sudokuPuzzle) {
        Solver.SolveSudoku(sudokuPuzzle);
        PrintSudoku(sudokuPuzzle);
    }

    public static void FixAllNumbers(SudokuSquare[][] sudokuPuzzle) {
        for (SudokuSquare[] row : sudokuPuzzle) {
            for (SudokuSquare square : row) {
                square.fixed = true;
            }
        }
    }

    public static void ExitProgram() {
        if (Sudoku.getInstance().getCurrentScene().getStateID() == StateID.PLAY) {
            ((PlayState) Sudoku.getInstance().getCurrentScene()).pause();
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (yesOrNo == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
}
