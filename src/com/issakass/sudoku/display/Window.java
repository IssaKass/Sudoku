package com.issakass.sudoku.display;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.display.dialogs.About;
import com.issakass.sudoku.engine.state.StateID;
import com.issakass.sudoku.engine.state.states.PlayState;
import com.issakass.sudoku.engine.theme.Theme;
import com.issakass.sudoku.utils.Helpers;
import com.issakass.sudoku.utils.ImportExport;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static com.issakass.sudoku.utils.Constants.ScreenConstants.LOGO_IMAGES;

public class Window extends JFrame {
    public Window(String title, GamePanel gamePanel) {
        this.setIconImages(LOGO_IMAGES);
        this.setTitle(title);
        this.add(gamePanel);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(createMenuBar());
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu, viewMenu, helpMenu;
        JMenuItem newGameMenuItem, importGameMenuItem, exportGameMenuItem, exitMenuItem;
        JRadioButtonMenuItem lightThemeMenuButton, darkThemeMenuButton;
        JCheckBoxMenuItem highlightMenuButton;
        JMenuItem aboutMenuItem;

        // Game Menu
        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic('G');

        newGameMenuItem = new JMenuItem("New");
        newGameMenuItem.setMnemonic('N');
        newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newGameMenuItem.addActionListener(e -> {
            if (Sudoku.getInstance().getCurrentScene().getStateID() == StateID.PLAY) {
                ((PlayState) Sudoku.getInstance().getCurrentScene()).pause();
                int yesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to open a new game?", "New Game", JOptionPane.YES_NO_OPTION);
                if (yesOrNo == JOptionPane.YES_OPTION) {
                    Sudoku.getInstance().changeState(StateID.OPTIONS);
                }
            } else {
                Sudoku.getInstance().changeState(StateID.OPTIONS);
            }
        });

        importGameMenuItem = new JMenuItem("Import");
        importGameMenuItem.setMnemonic('I');
        importGameMenuItem.addActionListener(e -> {
            ((PlayState) Sudoku.getInstance().getCurrentScene()).pause();
            if (Sudoku.getInstance().getCurrentScene().getStateID() == StateID.MENU)
                Sudoku.getInstance().changeState(StateID.PLAY);

            ((PlayState) Sudoku.getInstance().getCurrentScene()).getLogicManager()
                    .changeSudokuPuzzle(ImportExport.Import());
        });

        exportGameMenuItem = new JMenuItem("Export");
        exportGameMenuItem.setMnemonic('E');
        exportGameMenuItem.addActionListener(e -> {
            ((PlayState) Sudoku.getInstance().getCurrentScene()).pause();
            if (Sudoku.getInstance().getCurrentScene().getStateID() == StateID.PLAY) {
                ImportExport.Export(((PlayState) Sudoku.getInstance().getCurrentScene()).getLogicManager().getSudokuPuzzle());
            }
        });

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('x');
        exitMenuItem.addActionListener(e -> Helpers.ExitProgram());

        // Edit Menu
        viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');

        lightThemeMenuButton = new JRadioButtonMenuItem("Light Theme");
        lightThemeMenuButton.setMnemonic('L');
        lightThemeMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.ALT_DOWN_MASK));
        lightThemeMenuButton.addActionListener(e -> Sudoku.getInstance().changeTheme(Theme.LIGHT_THEME));

        darkThemeMenuButton = new JRadioButtonMenuItem("Dark Theme");
        darkThemeMenuButton.setMnemonic('D');
        darkThemeMenuButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_DOWN_MASK));
        darkThemeMenuButton.addActionListener(e -> Sudoku.getInstance().changeTheme(Theme.DARK_THEME));

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(lightThemeMenuButton);
        btnGroup.add(darkThemeMenuButton);
        switch (Sudoku.getInstance().getCurrentTheme()) {
            case LIGHT_THEME -> lightThemeMenuButton.setSelected(true);
            case DARK_THEME -> darkThemeMenuButton.setSelected(true);
        }

        highlightMenuButton = new JCheckBoxMenuItem("Highlight");
        highlightMenuButton.setMnemonic('H');
        highlightMenuButton.setSelected(true);
        highlightMenuButton.addActionListener(e -> {
            if (Sudoku.getInstance().getCurrentScene().getStateID() == StateID.PLAY) {
                ((PlayState) Sudoku.getInstance().getCurrentScene()).setHighlighted(highlightMenuButton.isSelected());
            }
        });

        // Help Menu
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');

        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.addActionListener(e -> new About().setVisible(true));

        // add menu items to menu
        gameMenu.add(newGameMenuItem);
        gameMenu.add(new JSeparator());
        gameMenu.add(importGameMenuItem);
        gameMenu.add(exportGameMenuItem);
        gameMenu.add(new JSeparator());
        gameMenu.add(exitMenuItem);

        viewMenu.add(lightThemeMenuButton);
        viewMenu.add(darkThemeMenuButton);
        viewMenu.add(new JSeparator());
        viewMenu.add(highlightMenuButton);

        helpMenu.add(aboutMenuItem);

        // add menu to menuBar
        menuBar.add(gameMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }
}
