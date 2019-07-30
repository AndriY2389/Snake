package main.java.ua.verkholiak.andrii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

class GameField extends JPanel implements ActionListener {

    private static final int FIELD_SIZE = 320;
    private static final int MAX_SNAKE_LENGTH = 50;

    private int delay = 200;
    private boolean gameRunning;

    private Snake snake;
    private Segment apple;
    private Wall walls;
    private Image segmentImage;
    private Image appleImage;
    private Timer timer;

    GameField() {
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    private void initGame() {
        gameRunning = true;
        snake = new Snake(new Segment(48, 48));
        walls = new Wall(FIELD_SIZE);
        placeAppleRandomly();

        timer = new Timer(delay, this);
        timer.start();
    }

    private void restartGame() {
        gameRunning = true;
        delay = 200;
        snake = new Snake(new Segment(48, 48));
        walls = new Wall(FIELD_SIZE);
        placeAppleRandomly();
        timer.setDelay(delay);
    }

    private void placeAppleRandomly() {
        do {
            apple = new Segment(new Random().nextInt(20) * Segment.SEGMENT_SIZE,
                    new Random().nextInt(20) * Segment.SEGMENT_SIZE);
        } while (walls.getWalls().contains(apple));
    }

    private void loadImages() {
        appleImage = new ImageIcon(getClass().getClassLoader().getResource("apple.png")).getImage();
        segmentImage = new ImageIcon(getClass().getClassLoader().getResource("dot.png")).getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            if (!snake.wasAppleEaten(apple)) {
                snake.removeLastSegment();
            } else {
                playSound("sound.wav");
                placeAppleRandomly();
                increaseGameSpeed();
            }

            if (snake.wasEatenByItself() || walls.getWalls().contains(snake.getHeadSegment())) {
                playSound("crash.wav");
                gameRunning = false;
            }

            snake.move();
        }

        repaint();
    }

    private void playSound(String soundFile) {
        new Thread(new MusicPlayer(soundFile)).start();
    }

    private void increaseGameSpeed() {
        if (delay > 100) {
            delay -= 10;
            timer.setDelay(delay);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (snake.getSegments().size() > MAX_SNAKE_LENGTH && gameRunning) {
            g.setColor(Color.white);
            g.drawString("You Win!!!", 125, (FIELD_SIZE / 2));
            timer.stop();
        } else if (gameRunning) {
            paintWalls(g);
            paintApple(g);
            paintSnake(g);
        } else {
            g.setColor(Color.white);
            g.drawString("You Lost", 125, (FIELD_SIZE / 2));
            restartGame();
        }
    }

    private void paintWalls(Graphics g) {
        walls.getWalls().forEach(x -> g.drawImage(segmentImage, x.getX(), x.getY(), this));
    }

    private void paintApple(Graphics g) {
        g.drawImage(appleImage, apple.getX(), apple.getY(), this);
    }

    private void paintSnake(Graphics g) {
        snake.getSegments().forEach(x -> g.drawImage(segmentImage, x.getX(), x.getY(), this));
    }

    private class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int pressedKey = e.getKeyCode();

            if (pressedKey == KeyEvent.VK_LEFT) {
                snake.setDirection(Direction.LEFT);
            }

            if (pressedKey == KeyEvent.VK_RIGHT) {
                snake.setDirection(Direction.RIGHT);
            }

            if (pressedKey == KeyEvent.VK_UP) {
                snake.setDirection(Direction.UP);
            }

            if (pressedKey == KeyEvent.VK_DOWN) {
                snake.setDirection(Direction.DOWN);
            }

            if (pressedKey == KeyEvent.VK_SPACE) {
                snake.reverse();
            }
        }
    }
}
