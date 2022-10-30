package com.issakass.sudoku.engine.logic;

import com.issakass.sudoku.engine.state.states.PlayState;
import com.issakass.sudoku.utils.Helpers;

public class LogicManager {
    // fields
    private Difficulty difficulty;
    private final PlayState playScene;
    private SudokuSquare[][] sudokuPuzzle;

    // constructors
    public LogicManager(PlayState playScene) {
        this.playScene = playScene;
        this.difficulty = Difficulty.Easy;
        this.sudokuPuzzle = Generator.GenerateSudoku(difficulty);
    }

    // methods
    public void init() {
        this.sudokuPuzzle = Generator.GenerateSudoku(difficulty);
    }

    public void update() {
        if (Solver.IsSolved(sudokuPuzzle)) {
            playScene.setSolved(true);
            Helpers.FixAllNumbers(sudokuPuzzle);
        }
    }

    // getters & setters
    public void changeDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.sudokuPuzzle = Generator.GenerateSudoku(difficulty);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public SudokuSquare[][] getSudokuPuzzle() {
        return sudokuPuzzle;
    }

    public void changeSudokuPuzzle(SudokuSquare[][] sudoku) {
        this.sudokuPuzzle = sudoku;
        playScene.reset();
    }
}
