package com.issakass.sudoku.engine.state;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Controller
        implements KeyListener, MouseListener, MouseMotionListener {

    protected State state;

    public Controller(State state) {
        this.state = state;
    }
}
