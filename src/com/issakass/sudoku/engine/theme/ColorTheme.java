package com.issakass.sudoku.engine.theme;

import java.awt.*;

public record ColorTheme(
        Color background,
        Color squareOutline,
        Color boxOutline,
        Color fixedNumbersColor,
        Color inputNumbersColor,
        Color wrongNumbersColor,
        Color squareFocus,
        Color zoneFocus,
        Color commonNumbersFocus,
        Color wrongNumberFocus) {

    public static final int ALPHA = 75;
}
