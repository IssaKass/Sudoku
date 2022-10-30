package com.issakass.sudoku.engine.state.states;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.engine.logic.LogicManager;
import com.issakass.sudoku.engine.state.State;
import com.issakass.sudoku.engine.state.StateID;
import com.issakass.sudoku.engine.state.controllers.PlayController;
import com.issakass.sudoku.engine.state.drawers.PlayDrawer;
import com.issakass.sudoku.engine.timer.PlayTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayState extends State {
    private final PlayTimer playTimer;
    private final PlayDrawer playDrawer;
    private final PlayController playController;
    private final LogicManager logicManager;

    private boolean paused = false;
    private boolean solved = false;
    private boolean highlighted = true;

    public PlayState() {
        super(StateID.PLAY);
        playController = new PlayController(this);
        playDrawer = new PlayDrawer(this);
        playTimer = new PlayTimer(this);
        logicManager = new LogicManager(this);
    }

    @Override
    public void init() {
        playDrawer.init();
        logicManager.init();
        playTimer.init();
    }

    @Override
    public void reset() {
        paused = false;
        solved = false;
        playTimer.reset();
        playTimer.start();
    }

    @Override
    public void update() {
        playTimer.update();
        logicManager.update();
    }

    @Override
    public void render(Graphics2D g2) {
        playDrawer.draw(g2);
    }

    // Listeners
    @Override
    public void keyTyped(KeyEvent e) {
        playController.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        playController.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playController.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        playController.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        playController.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        playController.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        playController.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        playController.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        playController.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        playController.mouseMoved(e);
    }

    // getters & setters
    public PlayDrawer getPlayDrawer() {
        return playDrawer;
    }

    public LogicManager getLogicManager() {
        return logicManager;
    }

    public PlayTimer getPlayTimer() {
        return playTimer;
    }

    public void pauseUnpause() {
        this.paused = !paused;
        ((JButton) Sudoku.getInstance().getGamePanel().getComponent(0)).setText(paused ? ">" : "||");
    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        this.paused = true;
        ((JButton) Sudoku.getInstance().getGamePanel().getComponent(0)).setText(paused ? ">" : "||");
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isHighlighted() {
        return highlighted;
    }
}
