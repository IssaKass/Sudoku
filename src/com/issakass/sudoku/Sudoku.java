package com.issakass.sudoku;

import com.issakass.sudoku.display.GamePanel;
import com.issakass.sudoku.display.Window;
import com.issakass.sudoku.engine.state.State;
import com.issakass.sudoku.engine.state.StateID;
import com.issakass.sudoku.engine.state.states.MenuState;
import com.issakass.sudoku.engine.state.states.OptionsState;
import com.issakass.sudoku.engine.state.states.PlayState;
import com.issakass.sudoku.engine.theme.Theme;

import java.awt.*;

import static com.issakass.sudoku.utils.Constants.ScreenConstants.SCREEN_TITLE;

public class Sudoku implements Runnable {
    private static Sudoku sudoku = null;

    private Window window;
    private GamePanel gamePanel;
    private State currentState;
    private Theme currentTheme;

    // System
    private Thread thread;
    private boolean running;
    private final int FPS = 120;
    private final int UPS = 200;

    private Sudoku() {
    }

    // get instance of singleton class Sudoku
    public static Sudoku getInstance() {
        if (Sudoku.sudoku == null) {
            Sudoku.sudoku = new Sudoku();
        }
        return Sudoku.sudoku;
    }

    // init the components
    public void init() {
        gamePanel = new GamePanel();

        changeTheme(Theme.LIGHT_THEME);
        changeState(StateID.MENU);

        window = new Window(SCREEN_TITLE, gamePanel);
    }

    public void changeState(StateID id) {
        switch (id) {
            case MENU -> currentState = new MenuState();
            case OPTIONS -> currentState = new OptionsState();
            case PLAY -> currentState = new PlayState();
        }
        currentState.init();
        gamePanel.initComponents();
    }

    public void changeTheme(Theme newTheme) {
        this.currentTheme = newTheme;
        gamePanel.setBackground(currentTheme.colorTheme.background());
    }

    // start game thread
    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    // stop game thread
    private synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // run game loop
    @Override
    public void run() {
        long previousTime = System.nanoTime();
        long currentTime;
        double timePerUpdate = 1E9 / UPS;
        double timePerFrame = 1E9 / FPS;
        double dtU = 0;
        double dtF = 0;

        int updates = 0;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (running) {
            currentTime = System.nanoTime();
            dtU += (currentTime - previousTime) / timePerUpdate;
            dtF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (dtU >= 1) {
                update();
                updates++;
                dtU--;
            }

            if (dtF >= 1) {
                gamePanel.repaint();
                frames++;
                dtF--;
            }

            if (System.currentTimeMillis() - lastCheck > 1000) {
                lastCheck += 1000;
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                updates = 0;
                frames = 0;
            }
        }
        stop();

    }

    // update game
    public void update() {
        currentState.update();
    }

    // render game
    public void render(Graphics2D g2) {
        currentState.render(g2);
    }

    // getters & setters
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public State getCurrentScene() {
        return currentState;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public Window getWindow() {
        return window;
    }

    public int getFPS() {
        return FPS;
    }

    public int getUPS() {
        return UPS;
    }
}
