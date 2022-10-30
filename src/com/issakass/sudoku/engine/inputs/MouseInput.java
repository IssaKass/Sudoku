package com.issakass.sudoku.engine.inputs;

import com.issakass.sudoku.Sudoku;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        Sudoku.getInstance().getCurrentScene().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Sudoku.getInstance().getCurrentScene().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Sudoku.getInstance().getCurrentScene().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Sudoku.getInstance().getCurrentScene().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Sudoku.getInstance().getCurrentScene().mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Sudoku.getInstance().getCurrentScene().mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Sudoku.getInstance().getCurrentScene().mouseMoved(e);
    }
}
