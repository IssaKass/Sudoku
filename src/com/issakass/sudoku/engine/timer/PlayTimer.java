package com.issakass.sudoku.engine.timer;

import com.issakass.sudoku.engine.state.states.PlayState;

import javax.swing.*;

public class PlayTimer {
    // fields
    private final PlayState playScene;
    private final Time time;
    private final Timer timer;

    // constructors
    public PlayTimer(PlayState playScene) {
        this.playScene = playScene;
        this.time = new Time();
        timer = new Timer(1000, e -> {
            time.seconds++;

            if (time.seconds == 60) {
                time.seconds = 0;
                time.minutes++;
            }
        });
    }

    // getters & setters
    public Time getTime() {
        return time;
    }

    // methods
    public void init() {
        time.reset();
    }

    public void reset() {
        time.reset();
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void update() {
        if (playScene.isSolved()) {
            stop();
            return;
        }

        if (!playScene.isPaused()) start();
        else stop();
    }

    @Override
    public String toString() {
        return time.toString();
    }
}
