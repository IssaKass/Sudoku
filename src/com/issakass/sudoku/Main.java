package com.issakass.sudoku;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    // Main Method --> entry of the program
    public static void main(String[] args) {
        FlatLaf.registerCustomDefaultsSource("com/issakass/themes");
        FlatLightLaf.setup();

        Sudoku sudoku = Sudoku.getInstance();
        sudoku.init();
        sudoku.start();
    }
}
