package com.issakass.sudoku.engine.state.controllers;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.engine.Focus;
import com.issakass.sudoku.engine.logic.LogicManager;
import com.issakass.sudoku.engine.logic.SudokuSquare;
import com.issakass.sudoku.engine.state.Controller;
import com.issakass.sudoku.engine.state.State;
import com.issakass.sudoku.engine.state.drawers.PlayDrawer;
import com.issakass.sudoku.engine.state.states.PlayState;
import com.issakass.sudoku.utils.Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.issakass.sudoku.utils.Constants.*;
import static java.awt.event.KeyEvent.*;

public class PlayController extends Controller {

    // constructors
    public PlayController(State state) {
        super(state);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // listeners
    public void keyPressed(KeyEvent e) {
        PlayState playScene = (PlayState) state;

        if (playScene.isSolved() || playScene.isPaused()) return;

        PlayDrawer playDrawer = playScene.getPlayDrawer();
        Focus focus = playDrawer.getFocus();
        LogicManager logicManager = ((PlayState) state).getLogicManager();
        SudokuSquare square;

        switch (e.getKeyCode()) {
            // move the cursor (row,col)
            case VK_LEFT -> {
                focus.setFocus();
                if (playDrawer.currentColumn - 1 >= 0) playDrawer.currentColumn--;
            }
            case VK_RIGHT -> {
                focus.setFocus();
                if (playDrawer.currentColumn + 1 < GRID) playDrawer.currentColumn++;
            }
            case VK_UP -> {
                focus.setFocus();
                if (playDrawer.currentRow - 1 >= 0) playDrawer.currentRow--;
            }
            case VK_DOWN -> {
                focus.setFocus();
                if (playDrawer.currentRow + 1 < GRID) playDrawer.currentRow++;
            }
            /*
                Put a number in the specified square using keys 1 to 9.
                ex: if you entered 1, and you entered 1 again in the same square, the square will be cleared
             */
            case VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9 -> {
                if (Helpers.IsCoordInsideBounds(playDrawer.currentRow, playDrawer.currentColumn)) {
                    square = logicManager.getSudokuPuzzle()[playDrawer.currentRow][playDrawer.currentColumn];
                    if (!square.fixed) {
                        int input = Character.getNumericValue(e.getKeyChar());
                        if (square.value == input || input <= 0 || input > GRID) {
                            square.value = 0;
                            break;
                        }
                        square.value = input;
                    }
                }
            }
            // delete a number using DELETE key
            case VK_DELETE -> {
                if (Helpers.IsCoordInsideBounds(playDrawer.currentRow, playDrawer.currentColumn)) {
                    square = logicManager.getSudokuPuzzle()[playDrawer.currentRow][playDrawer.currentColumn];
                    if (!square.fixed) {
                        square.value = 0;
                    }
                }
            }
            // remove the focus when esc is pressed
            case VK_ESCAPE -> focus.removeFocus();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3 && !((PlayState) state).isSolved()) {
            ((PlayState) state).getPlayDrawer().getFocus().removeFocus();
        }
    }

    public void mousePressed(MouseEvent e) {
        if (((PlayState) state).isPaused()) return;
        if (e.getButton() != MouseEvent.BUTTON1) return;

        if (BoardConstants.BOARD_BOUNDS.contains(e.getPoint()) && !((PlayState) state).isPaused()) {
            PlayDrawer playDrawer = ((PlayState) state).getPlayDrawer();
            playDrawer.getFocus().setFocus();
            playDrawer.currentRow = (e.getY() - BoardConstants.BOARD_Y) / SQUARE_SIZE;
            playDrawer.currentColumn = (e.getX() - BoardConstants.BOARD_X) / SQUARE_SIZE;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Sudoku.getInstance().getGamePanel().requestFocus();

        if (!((PlayState) state).isPaused()) {
            if (BoardConstants.BOARD_BOUNDS.contains(e.getPoint()) && !((PlayState) state).isSolved())
                Sudoku.getInstance().getWindow().setCursor(Cursors.HAND_CURSOR);
            else
                Sudoku.getInstance().getWindow().setCursor(Cursors.DEFAULT_CURSOR);
        } else {
            Sudoku.getInstance().getWindow().setCursor(Cursors.DEFAULT_CURSOR);
        }
    }
}
