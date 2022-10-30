package com.issakass.sudoku.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class Loader {
    public static InputStream GetFileAsStream(String fileName) {
        ClassLoader classLoader = Loader.class.getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File '" + fileName + "' not found!");
        } else {
            return inputStream;
        }
    }

    public static File GetFile(String fileName) {
        ClassLoader classLoader = Loader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("File '" + fileName + "' not found");
        } else {
            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Image GetImage(String fileName) {
        ClassLoader classLoader = Loader.class.getClassLoader();
        Image img;
        try {
            img = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    public static ImageIcon GetIcon(String fileName) {
        return new ImageIcon(GetImage(fileName));
    }

    public static Font GetFont(String fileName, int fontFormat) {
        try {
            return Font.createFont(fontFormat, GetFileAsStream(fileName));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
