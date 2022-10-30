package com.issakass.sudoku.utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class Constants {
    // Game Name
    public static final String GAME_NAME = "Sudoku";

    // Grid and Box
    public static final int BOX = 3;
    public static final int GRID = 9;

    // Sizes
    public static final int SQUARE_SIZE = 60;
    public static final int BOX_SIZE = BOX * SQUARE_SIZE;
    public static final int GRID_SIZE = SQUARE_SIZE * GRID;

    // Fonts
    public static final Font MAIN_FONT = Loader.GetFont("fonts/ARLRDBD.TTF", Font.TRUETYPE_FONT);

    public static class MenuBoardConstants {
        public static final int MENU_SQUARE_SIZE = 40;
        public static final int MENU_BOARD_SIZE = GRID * MENU_SQUARE_SIZE;
        public static final int MENU_BOARD_X = (ScreenConstants.SCREEN_WIDTH - GRID * MENU_SQUARE_SIZE) / 2;
        public static final int MENU_BOARD_Y = 4 * MENU_SQUARE_SIZE;
        public static final Rectangle MENU_BOARD_BOUNDS = new Rectangle(
                MENU_BOARD_X, MENU_BOARD_Y, MENU_BOARD_SIZE, MENU_BOARD_SIZE);
    }

    public static class BoardConstants {
        public static final int BOARD_X = SQUARE_SIZE * 2;
        public static final int BOARD_Y = SQUARE_SIZE;
        public static final Rectangle BOARD_BOUNDS = new Rectangle(BOARD_X, BOARD_Y, GRID_SIZE, GRID_SIZE);
    }

    public static class ScreenConstants {
        public static final String SCREEN_TITLE = "Sudoku Game";
        public static final int SCREEN_WIDTH = GRID_SIZE + 4 * SQUARE_SIZE;
        public static final int SCREEN_HEIGHT = GRID_SIZE + 3 * SQUARE_SIZE;
        public static final List<Image> LOGO_IMAGES = List.of(
                Loader.GetImage("images/logo/logo16x16.png"),
                Loader.GetImage("images/logo/logo32x32.png"),
                Loader.GetImage("images/logo/logo64x64.png"),
                Loader.GetImage("images/logo/logo128x128.png")
        );
    }

    public static class Cursors {
        public static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
        public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    }


}
