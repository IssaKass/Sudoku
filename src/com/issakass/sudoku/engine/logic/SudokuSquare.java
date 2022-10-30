package com.issakass.sudoku.engine.logic;

public class SudokuSquare {
    public int value;
    public boolean fixed;

    public SudokuSquare(int value, boolean fixed) {
        this.value = value;
        this.fixed = fixed;
    }

    public SudokuSquare(int value) {
        this(value, false);
    }
}
