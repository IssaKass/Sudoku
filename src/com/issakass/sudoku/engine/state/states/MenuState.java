package com.issakass.sudoku.engine.state.states;

import com.issakass.sudoku.engine.state.State;
import com.issakass.sudoku.engine.state.StateID;
import com.issakass.sudoku.engine.state.controllers.MenuController;
import com.issakass.sudoku.engine.state.drawers.MenuDrawer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState extends State {
    private final MenuDrawer menuDrawer;
    private final MenuController menuController;

    public MenuState() {
        super(StateID.MENU);
        menuDrawer = new MenuDrawer(this);
        menuController = new MenuController(this);
    }

    @Override
    public void init() {
        menuDrawer.init();
    }

    @Override
    public void reset() {
        menuDrawer.reset();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        menuDrawer.draw(g2);
    }


    // Listeners
    @Override
    public void keyTyped(KeyEvent e) {
        menuController.keyTyped(e);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        menuController.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        menuController.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        menuController.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        menuController.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        menuController.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        menuController.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        menuController.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        menuController.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuController.mouseMoved(e);
    }

    // getters & setters
    public MenuDrawer getMenuDrawer() {
        return menuDrawer;
    }

    public MenuController getMenuController() {
        return menuController;
    }
}
