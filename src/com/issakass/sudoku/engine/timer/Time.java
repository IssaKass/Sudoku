package com.issakass.sudoku.engine.timer;

import java.text.DecimalFormat;

public class Time {
    // fields
    int minutes;
    int seconds;
    private final DecimalFormat decimalFormat = new DecimalFormat("00");

    // constructors
    public Time(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Time(int minutes) {
        this(minutes, 0);
    }

    public Time() {
        this(0, 0);
    }

    // methods
    public void reset() {
        this.minutes = 0;
        this.seconds = 0;
    }

    @Override
    public String toString() {
        return decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds);
    }
}
