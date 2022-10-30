package com.issakass.sudoku.engine;

public class Focus {
    private boolean focused;

    public void removeFocus() {
        if (focused) focused = false;
    }

    public void setFocus() {
        if (!focused) focused = true;
    }

    public boolean hasFocus() {
        return focused;
    }
}
