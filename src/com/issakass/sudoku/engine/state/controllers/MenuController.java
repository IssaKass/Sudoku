package com.issakass.sudoku.engine.state.controllers;

import com.issakass.sudoku.engine.state.Controller;
import com.issakass.sudoku.engine.state.State;
import com.issakass.sudoku.engine.state.drawers.MenuDrawer;
import com.issakass.sudoku.engine.state.states.MenuState;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.issakass.sudoku.utils.Constants.*;

public class MenuController extends Controller {
    // constructors
    public MenuController(State state) {
        super(state);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
        MenuDrawer menuDrawer = ((MenuState) state).getMenuDrawer();
        if (MenuBoardConstants.MENU_BOARD_BOUNDS.contains(e.getPoint())) {
            menuDrawer.currentRow = (e.getY() - MenuBoardConstants.MENU_BOARD_Y) / MenuBoardConstants.MENU_SQUARE_SIZE;
            menuDrawer.currentColumn = (e.getX() - MenuBoardConstants.MENU_BOARD_X) / MenuBoardConstants.MENU_SQUARE_SIZE;
        } else {
            menuDrawer.currentRow = -1;
            menuDrawer.currentColumn = -1;
        }
    }
}
