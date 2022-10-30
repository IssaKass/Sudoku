package com.issakass.sudoku.utils;

import com.issakass.sudoku.Sudoku;
import com.issakass.sudoku.engine.logic.SudokuSquare;
import com.issakass.sudoku.engine.state.states.PlayState;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;

import static com.issakass.sudoku.utils.Constants.GRID;

public class ImportExport {
    public static SudokuSquare[][] Import() {
        SudokuSquare[][] sudoku = new SudokuSquare[GRID][GRID];

        JFileChooser f = new JFileChooser(FileSystemView.getFileSystemView());
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text Files", "txt");
        f.addChoosableFileFilter(restrict);

        int r = f.showOpenDialog(null);

        if (f.getSelectedFile() == null) {
            return ((PlayState) Sudoku.getInstance().getCurrentScene()).getLogicManager().getSudokuPuzzle();
        }

        if (r == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f.getSelectedFile()));
                int row = 0, col = 0, number;
                String[] lineNumbers;
                while (row < GRID) {
                    lineNumbers = reader.readLine().split("");
                    while (col < GRID) {
                        number = Integer.parseInt(lineNumbers[col]);
                        sudoku[row][col] = new SudokuSquare(number, number != 0);
                        col++;
                    }
                    col = 0;
                    row++;
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sudoku;
    }

    public static void Export(SudokuSquare[][] sudoku) {
        JFileChooser f = new JFileChooser(FileSystemView.getFileSystemView());
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text Files", "txt");
        f.addChoosableFileFilter(restrict);

        int r = f.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(f.getSelectedFile() + ".txt"));
                int row, col;
                for (row = 0; row < GRID; row++) {
                    for (col = 0; col < GRID; col++) {
                        writer.write(String.valueOf(sudoku[row][col].value));
                    }
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
