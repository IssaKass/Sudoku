package com.issakass.sudoku.display;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.engine.inputs.KeyInput;
import com.issakass.sudoku.engine.inputs.MouseInput;
import com.issakass.sudoku.engine.logic.Difficulty;
import com.issakass.sudoku.engine.logic.LogicManager;
import com.issakass.sudoku.engine.state.StateID;
import com.issakass.sudoku.engine.state.states.PlayState;
import com.issakass.sudoku.utils.Helpers;

import javax.swing.*;
import java.awt.*;

import static com.issakass.sudoku.utils.Constants.SQUARE_SIZE;
import static com.issakass.sudoku.utils.Constants.ScreenConstants.SCREEN_HEIGHT;
import static com.issakass.sudoku.utils.Constants.ScreenConstants.SCREEN_WIDTH;

public class GamePanel extends JPanel {

    public GamePanel() {
        Dimension dimension = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setLayout(null);

        MouseInput mouseInput = new MouseInput();
        KeyInput keyInput = new KeyInput();
        this.requestFocus();
        this.setFocusable(true);
        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        Sudoku.getInstance().render(g2);
    }

    public void initComponents() {
        removeAll();
        switch (Sudoku.getInstance().getCurrentScene().getStateID()) {
            case MENU -> addMenuStateComponents();
            case OPTIONS -> addOptionsStateComponents();
            case PLAY -> addPlayStateComponents();
        }
    }

    private void addMenuStateComponents() {
        Font font = Sudoku.getInstance().getCurrentTheme().font.deriveFont(20F);
        JButton playButton, quitButton;

        int width = (int) (2.5 * SQUARE_SIZE);
        int height = 2 * SQUARE_SIZE / 3;
        int x = (SCREEN_WIDTH - width) / 2;
        int y = SCREEN_HEIGHT - 150;

        playButton = new JButton("Play");
        playButton.setFont(font);
        playButton.setBounds(x, y, width, height);
        playButton.addActionListener(e -> Sudoku.getInstance().changeState(StateID.OPTIONS));

        y += SQUARE_SIZE;
        quitButton = new JButton("Quit");
        quitButton.setFont(font);
        quitButton.setBounds(x, y, width, height);
        quitButton.addActionListener(e -> Helpers.ExitProgram());

        this.add(playButton);
        this.add(quitButton);
    }

    private void addOptionsStateComponents() {
        Font font = Sudoku.getInstance().getCurrentTheme().font.deriveFont(20F);
        JButton easyButton, mediumButton, hardButton, backButton;

        int width = (int) (2.5 * SQUARE_SIZE);
        int height = 2 * SQUARE_SIZE / 3;
        int x = (SCREEN_WIDTH - width) / 2;
        int y = (SCREEN_HEIGHT - 6 * SQUARE_SIZE) / 2;

        easyButton = new JButton("Easy");
        easyButton.setFont(font);
        easyButton.setBounds(x, y, width, height);
        easyButton.addActionListener(e -> play(Difficulty.Easy));

        y += 2 * SQUARE_SIZE;
        mediumButton = new JButton("Medium");
        mediumButton.setFont(font);
        mediumButton.setBounds(x, y, width, height);
        mediumButton.addActionListener(e -> play(Difficulty.Medium));

        y += 2 * SQUARE_SIZE;
        hardButton = new JButton("Hard");
        hardButton.setFont(font);
        hardButton.setBounds(x, y, width, height);
        hardButton.addActionListener(e -> play(Difficulty.Hard));

        width = SQUARE_SIZE;
        height = 3 * SQUARE_SIZE / 4;
        x = SQUARE_SIZE / 2;
        y = SCREEN_HEIGHT - height - SQUARE_SIZE / 2;

        backButton = new JButton("<-");
        backButton.setFont(font);
        backButton.setBounds(x, y, width, height);
        backButton.addActionListener(e -> Sudoku.getInstance().changeState(StateID.MENU));

        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);
        this.add(backButton);
    }

    private void addPlayStateComponents() {
        int x, y, width, height;
        Font font = Sudoku.getInstance().getCurrentTheme().font.deriveFont(20F);
        JButton pauseButton, backButton;

        width = (int) (2.5 * SQUARE_SIZE);
        height = 2 * SQUARE_SIZE / 3;
        x = (SCREEN_WIDTH - width) / 2;
        y = SCREEN_HEIGHT - height / 2 - SQUARE_SIZE;

        pauseButton = new JButton("||");
        pauseButton.setFont(font);
        pauseButton.setBounds(x, y, width, height);
        pauseButton.addActionListener(e -> {
            if (Sudoku.getInstance().getCurrentScene().getStateID() == StateID.PLAY) {
                ((PlayState) Sudoku.getInstance().getCurrentScene()).pauseUnpause();
            }
        });

        width = SQUARE_SIZE;
        height = 3 * SQUARE_SIZE / 4;
        x = SQUARE_SIZE / 2;
        y = SCREEN_HEIGHT - height - SQUARE_SIZE / 2;

        backButton = new JButton("<-");
        backButton.setFont(font);
        backButton.setBounds(x, y, width, height);
        backButton.addActionListener(e -> {
            ((PlayState) Sudoku.getInstance().getCurrentScene()).pause();
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to exit game?", "Options", JOptionPane.YES_NO_OPTION);
            if (yesOrNo == JOptionPane.YES_OPTION) {
                Sudoku.getInstance().changeState(StateID.OPTIONS);
            }
        });

        this.add(pauseButton);
        this.add(backButton);
    }

    public void play(Difficulty difficulty) {
        Sudoku.getInstance().changeState(StateID.PLAY);
        LogicManager logicManager = ((PlayState) Sudoku.getInstance().getCurrentScene()).getLogicManager();
        logicManager.changeDifficulty(difficulty);
    }
}
