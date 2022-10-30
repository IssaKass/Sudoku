package com.issakass.sudoku.engine.theme;

import java.awt.*;

import static com.issakass.sudoku.engine.theme.ColorTheme.ALPHA;
import static com.issakass.sudoku.utils.Constants.MAIN_FONT;

public enum Theme {
    LIGHT_THEME(new ColorTheme(
            new Color(250, 250, 250),           // background
            new Color(150, 150, 150),           // square outline
            Color.DARK_GRAY,                    // box outline
            Color.DARK_GRAY,                    // fixed numbers color
            new Color(24, 115, 232),            // input numbers color
            new Color(200, 20, 20),             // wrong numbers color
            new Color(168, 224, 255, ALPHA),    // square focus
            new Color(143, 181, 217, ALPHA),    // zone focus
            new Color(43, 79, 159, ALPHA),      // common numbers focus
            new Color(255, 137, 137, ALPHA)     // wrong numbers focus
    ), MAIN_FONT.deriveFont(Font.BOLD, 36)),

    // TODO adjust colors
    DARK_THEME(new ColorTheme(
            new Color(35, 35, 35),              // background
            new Color(180, 180, 180),           // square outline
            new Color(220, 220, 220),           // box outline
            new Color(255, 255, 255),           // fixed numbers color
            new Color(255, 231, 39),            // input numbers color
            new Color(255, 90, 90),             // wrong numbers color
            new Color(220, 220, 220, ALPHA),    // square focus
            new Color(120, 120, 120, ALPHA),    // zone focus
            new Color(240, 240, 240, ALPHA),    // common numbers focus
            new Color(255, 170, 170, ALPHA)     // wrong number focus
    ), MAIN_FONT.deriveFont(Font.BOLD, 36));

    public final ColorTheme colorTheme;
    public final Font font;

    Theme(ColorTheme colorTheme, Font font) {
        this.colorTheme = colorTheme;
        this.font = font;
    }
}
