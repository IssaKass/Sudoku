package com.issakass.sudoku.engine.inputs;

import com.issakass.sudoku.Sudoku;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        Sudoku.getInstance().getCurrentScene().keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Sudoku.getInstance().getCurrentScene().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Sudoku.getInstance().getCurrentScene().keyReleased(e);
    }
}
