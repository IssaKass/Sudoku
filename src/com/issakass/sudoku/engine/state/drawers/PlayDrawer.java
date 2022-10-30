package com.issakass.sudoku.engine.state.drawers;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.engine.Focus;
import com.issakass.sudoku.engine.logic.LogicManager;
import com.issakass.sudoku.engine.logic.Solver;
import com.issakass.sudoku.engine.logic.SudokuSquare;
import com.issakass.sudoku.engine.state.Drawer;
import com.issakass.sudoku.engine.state.State;
import com.issakass.sudoku.engine.state.StateID;
import com.issakass.sudoku.engine.state.states.PlayState;
import com.issakass.sudoku.engine.theme.ColorTheme;
import com.issakass.sudoku.engine.theme.Theme;
import com.issakass.sudoku.engine.timer.Time;
import com.issakass.sudoku.utils.Helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.issakass.sudoku.utils.Constants.*;

public class PlayDrawer extends Drawer {
    // fields
    private final Focus focus;
    public int currentRow = 4, currentColumn = 4;

    // constructors
    public PlayDrawer(State state) {
        super(state);
        this.focus = new Focus();
    }

    // getters & setters
    public Focus getFocus() {
        return focus;
    }

    // initialize the drawer
    @Override
    public void init() {
        focus.removeFocus();
    }

    @Override
    public void reset() {
        focus.removeFocus();
    }

    @Override
    public void draw(Graphics2D g2) {
        Theme theme = Sudoku.getInstance().getCurrentTheme();

        g2.translate(BoardConstants.BOARD_X, BoardConstants.BOARD_Y);
        drawSudokuBoard(g2, theme);

        if (((PlayState) state).isSolved()) {
            drawSolved(g2, theme);
        }

        g2.translate(-BoardConstants.BOARD_X, -BoardConstants.BOARD_Y);
    }

    // Draws the focus on current square
    private void drawFocusSquare(Graphics2D g2, Color squareFocus) {
        g2.setColor(squareFocus);
        g2.fillRect(currentColumn * SQUARE_SIZE, currentRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    // Draws the highlighted zones (square, row, column and box)
    private void drawFocusZone(Graphics2D g2, Color zoneFocus) {
        if (!focus.hasFocus() || !((PlayState) state).isHighlighted()) return;

        int i, row, col;
        g2.setColor(zoneFocus);

        // row zone
        for (i = 0; i < GRID; i++) {
            if (i == currentColumn) continue;
            g2.fillRect(i * SQUARE_SIZE, currentRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }

        // column zone
        for (i = 0; i < GRID; i++) {
            if (i == currentRow) continue;
            g2.fillRect(currentColumn * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }

        // box zone
        int localBoxRow = currentRow - currentRow % 3;
        int localBoxColumn = currentColumn - currentColumn % 3;

        for (i = 0; i < GRID; i++) {
            row = localBoxRow + i / 3;
            col = localBoxColumn + i % 3;

            if (currentRow == row || currentColumn == col) continue;
            g2.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    private void highlightCommonNumbers(Graphics2D g2, Color color, LogicManager logicManager) {
        if (!focus.hasFocus() || !((PlayState) state).isHighlighted()) return;

        SudokuSquare square = logicManager.getSudokuPuzzle()[currentRow][currentColumn];
        if (square.value == 0) return;

        g2.setColor(color);
        int i, row, col;
        for (i = 0; i < GRID * GRID; i++) {
            row = i / GRID;
            col = i % GRID;

            if (currentRow == row && currentColumn == col) continue;
            if (logicManager.getSudokuPuzzle()[row][col].value == square.value) {
                g2.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void highlightWrongNumbers(Graphics2D g2, Color color, LogicManager logicManager) {
        g2.setColor(color);
        SudokuSquare square;
        int i, row, col;
        for (i = 0; i < GRID * GRID; i++) {
            row = i / GRID;
            col = i % GRID;
            square = logicManager.getSudokuPuzzle()[row][col];

            if (!Solver.IsNumberValid(Helpers.CopySquareValues(logicManager.getSudokuPuzzle()), square.value, row, col)
                    && square.value != 0 && !square.fixed) {
                g2.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    // Draws the numbers (fixed and inputs)
    private void drawNumbers(Graphics2D g2, Color fixedNumberColor, Color inputNumberColor, Color wrongNumberColor, Font font, LogicManager logicManager) {
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D strBounds;

        int i, row, col;
        SudokuSquare square;
        String str;
        int xOffset, yOffset;

        for (i = 0; i < GRID * GRID; i++) {
            row = i / GRID;
            col = i % GRID;

            square = logicManager.getSudokuPuzzle()[row][col];
            if (square.value == 0) continue;

            str = String.valueOf(square.value);
            strBounds = fm.getStringBounds(str, g2);
            xOffset = (int) ((SQUARE_SIZE - strBounds.getWidth()) / 2);
            yOffset = (int) ((SQUARE_SIZE - strBounds.getHeight()) / 2 + fm.getAscent());

            if (square.fixed) g2.setColor(fixedNumberColor);
            else {
                if (!Solver.IsNumberValid(Helpers.CopySquareValues(logicManager.getSudokuPuzzle()), square.value, row, col))
                    g2.setColor(wrongNumberColor);
                else
                    g2.setColor(inputNumberColor);
            }
            g2.drawString(str, xOffset + col * SQUARE_SIZE, yOffset + row * SQUARE_SIZE);
        }
    }

    // Draws the time
    private void drawTime(Graphics2D g2, Color color, Font font) {
        Time currentTime = ((PlayState) state).getPlayTimer().getTime();

        int x = GRID_SIZE + SQUARE_SIZE / 2;
        int y = -SQUARE_SIZE / 2;

        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(currentTime.toString(), x, y);
    }

    // Draws the sudoku board
    private void drawSudokuBoard(Graphics2D g2, Theme theme) {
        ColorTheme colorTheme = theme.colorTheme;
        Font font = theme.font;
        PlayState playScene = (PlayState) state;
        LogicManager logicManager = playScene.getLogicManager();

        if (!playScene.isPaused()) {
            if (!playScene.isSolved()) {
                highlightCommonNumbers(g2, colorTheme.commonNumbersFocus(), logicManager);
                highlightWrongNumbers(g2, colorTheme.wrongNumberFocus(), logicManager);
                drawFocusSquare(g2, colorTheme.squareFocus());
                drawFocusZone(g2, colorTheme.zoneFocus());
            }
            drawSquares(g2, colorTheme.squareOutline(), SQUARE_SIZE);
            drawNumbers(g2, colorTheme.fixedNumbersColor(), colorTheme.inputNumbersColor(), colorTheme.wrongNumbersColor(),
                    font, logicManager);
        }

        drawTime(g2, colorTheme.fixedNumbersColor(), font.deriveFont(24F));
        draw3x3Box(g2, colorTheme.boxOutline(), new BasicStroke(3), BOX_SIZE);
    }

    private void drawSolved(Graphics2D g2, Theme theme) {
        // background
        g2.setColor(theme.colorTheme.inputNumbersColor());
        g2.fillRect(0, 0, GRID_SIZE, GRID_SIZE);

        int x, y;
        String msg;

        // excellent
        g2.setFont(theme.font.deriveFont(44F));
        g2.setColor(theme.colorTheme.background());
        FontMetrics fm = g2.getFontMetrics();

        msg = "Excellent";
        x = (GRID_SIZE - fm.stringWidth(msg)) / 2;
        y = (GRID_SIZE - 4 * SQUARE_SIZE) / 2;
        g2.drawString(msg, x, y);

        // difficulty
        g2.setFont(theme.font.deriveFont(Font.PLAIN, 20F));
        fm = g2.getFontMetrics();

        msg = "Difficulty";
        x = 2 * SQUARE_SIZE;
        y += 2 * SQUARE_SIZE;
        g2.drawString(msg, x, y);

        msg = ((PlayState) state).getLogicManager().getDifficulty().name();
        x = (7 * SQUARE_SIZE - fm.stringWidth(msg));
        g2.drawString(msg, x, y);

        // line
        y += SQUARE_SIZE / 3;
        g2.drawLine(2 * SQUARE_SIZE, y, 7 * SQUARE_SIZE, y);

        // time
        msg = "Time";
        x = 2 * SQUARE_SIZE;
        y += SQUARE_SIZE / 2;
        g2.drawString(msg, x, y);

        msg = ((PlayState) state).getPlayTimer().getTime().toString();
        x = (7 * SQUARE_SIZE - fm.stringWidth(msg));
        g2.drawString(msg, x, y);

        JButton menuButton = (JButton) Sudoku.getInstance().getGamePanel().getComponent(0);
        menuButton.setText("Menu");
        menuButton.addActionListener(e -> Sudoku.getInstance().changeState(StateID.MENU));
    }
}
