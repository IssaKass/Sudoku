package com.issakass.sudoku.engine.state;

import java.awt.*;

import static com.issakass.sudoku.utils.Constants.*;

public abstract class Drawer {
    protected State state;

    public Drawer(State state) {
        this.state = state;
    }

    public abstract void init();

    public abstract void reset();

    public abstract void draw(Graphics2D g2);

    // Draws the 81 squares
    public void drawSquares(Graphics2D g2, Color color, int squareSize) {
        g2.setColor(color);

        int i;
        for (i = 0; i <= GRID; i++) {
            g2.drawLine(0, i * squareSize, GRID * squareSize, i * squareSize);
            g2.drawLine(i * squareSize, 0, i * squareSize, GRID * squareSize);
        }
    }

    // Draws the 3x3 boxes
    public void draw3x3Box(Graphics2D g2, Color color, Stroke stroke, int boxSize) {
        Stroke resetStroke = g2.getStroke();
        g2.setStroke(stroke);
        g2.setColor(color);

        int i;
        for (i = 0; i <= BOX; i++) {
            g2.drawLine(0, i * boxSize, BOX * boxSize, i * boxSize);
            g2.drawLine(i * boxSize, 0, i * boxSize, BOX * boxSize);
        }
        g2.setStroke(resetStroke);
    }
}
