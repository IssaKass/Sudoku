package com.issakass.sudoku.engine.state;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class State implements KeyListener, MouseListener, MouseMotionListener {
    private final StateID stateID;

    public State(StateID stateID) {
        this.stateID = stateID;
    }

    public abstract void init();

    public abstract void reset();

    public abstract void update();

    public abstract void render(Graphics2D g2);

    public StateID getStateID() {
        return stateID;
    }
}
