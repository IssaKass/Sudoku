package com.issakass.sudoku.engine.logic;

public enum Difficulty {
    Easy(40), Medium(30), Hard(20);

    public final int startNumbers;

    Difficulty(int startNumbers) {
        this.startNumbers = startNumbers;
    }
}
