package com.issakass.sudoku.display.dialogs;

import javax.swing.*;

import static com.issakass.sudoku.utils.Constants.ScreenConstants.LOGO_IMAGES;

public class About extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public About() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("About");
        setIconImages(LOGO_IMAGES);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        setResizable(false);
        pack();
        setLocationRelativeTo(getParent());
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
