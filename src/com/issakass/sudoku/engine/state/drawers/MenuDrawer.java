package com.issakass.sudoku.engine.state.drawers;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.engine.logic.Generator;
import com.issakass.sudoku.engine.state.Drawer;
import com.issakass.sudoku.engine.state.State;
import com.issakass.sudoku.engine.theme.ColorTheme;
import com.issakass.sudoku.engine.theme.Theme;
import com.issakass.sudoku.utils.Helpers;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.issakass.sudoku.utils.Constants.*;
import static com.issakass.sudoku.utils.Constants.MenuBoardConstants.*;
import static com.issakass.sudoku.utils.Constants.ScreenConstants.SCREEN_WIDTH;

public class MenuDrawer extends Drawer {
    private float counter = 0;
    final float DELAY = 100;
    int[][] numbers;
    public int currentRow = -1, currentColumn = -1;

    // constructors
    public MenuDrawer(State state) {
        super(state);
        numbers = Generator.GenerateBoard();
    }

    // initialize the drawer
    @Override
    public void init() {
        counter = 0;
    }

    @Override
    public void reset() {
        counter = 0;
    }

    @Override
    public void draw(Graphics2D g2) {
        Theme theme = Sudoku.getInstance().getCurrentTheme();

        drawLogo(g2, theme);

        g2.translate(MENU_BOARD_X, MENU_BOARD_Y);
        drawSudokuBoard(g2, theme);
        g2.translate(-MENU_BOARD_X, -MENU_BOARD_Y);
    }

    private void drawLogo(Graphics2D g2, Theme theme) {
        g2.setFont(theme.font.deriveFont(Font.BOLD, 50F));
        g2.setColor(theme.colorTheme.fixedNumbersColor());
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(GAME_NAME, (SCREEN_WIDTH - fm.stringWidth(GAME_NAME)) / 2, (int) (1.5 * SQUARE_SIZE));
    }

    private void drawSudokuBoard(Graphics2D g2, Theme theme) {
        ColorTheme colorTheme = theme.colorTheme;

        if (++counter >= DELAY) {
            counter = 0;
            numbers = Generator.GenerateBoard();
        }

        drawFocusSquare(g2, colorTheme.squareFocus());
        drawSquares(g2, colorTheme.squareOutline(), MenuBoardConstants.MENU_SQUARE_SIZE);
        draw3x3Box(g2, colorTheme.boxOutline(), new BasicStroke(3), BOX * MenuBoardConstants.MENU_SQUARE_SIZE);
        drawRandomNumbers(g2, colorTheme.fixedNumbersColor(), theme.font);
    }

    private void drawFocusSquare(Graphics2D g2, Color squareFocus) {
        if (Helpers.IsCoordInsideBounds(currentRow, currentColumn)) {
            g2.setColor(squareFocus);
            g2.fillRect(currentColumn * MenuBoardConstants.MENU_SQUARE_SIZE, currentRow * MenuBoardConstants.MENU_SQUARE_SIZE,
                    MenuBoardConstants.MENU_SQUARE_SIZE, MenuBoardConstants.MENU_SQUARE_SIZE);
        }
    }

    private void drawRandomNumbers(Graphics2D g2, Color numbersColor, Font font) {
        g2.setFont(font.deriveFont(20F));
        g2.setColor(numbersColor);
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D strBounds;

        int i, row, col;
        String str;
        int xOffset, yOffset;

        for (i = 0; i < GRID * GRID; i++) {
            row = i / GRID;
            col = i % GRID;

            str = String.valueOf(numbers[row][col]);
            strBounds = fm.getStringBounds(str, g2);
            xOffset = (int) ((MenuBoardConstants.MENU_SQUARE_SIZE - strBounds.getWidth()) / 2);
            yOffset = (int) ((MenuBoardConstants.MENU_SQUARE_SIZE - strBounds.getHeight()) / 2 + fm.getAscent());

            g2.drawString(str, xOffset + col * MenuBoardConstants.MENU_SQUARE_SIZE, yOffset + row * MenuBoardConstants.MENU_SQUARE_SIZE);
        }
    }

}
