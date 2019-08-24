package main.java.ua.verkholiak.andrii;

import javax.swing.*;

public class MainWindow extends JFrame {

    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 375;

    private MainWindow() {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocation(400, 200);
        add(new GameField());
        setVisible(true);
        playMusic();
    }

    private void playMusic() {
        new Thread(new MusicPlayer("Shadilay.wav")).start();
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
