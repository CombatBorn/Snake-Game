package testing.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import static testing.Snake.SnakeGame.*;

public class GameGraphics extends JPanel {

    public static Graphics graphics;

    public GameGraphics() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter());
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        GameGraphics.graphics = graphics;
        super.paintComponent(GameGraphics.graphics);
        generateFrame();
    }

    public void generateFrame() {
        if (!ALIVE) {

            if (SNAKE != null) {
                graphics.setColor(Color.WHITE);
                String displayText = "SCORE: " + (SNAKE.size() - 3);
                Font font = new Font(null, 1, 40);

                FontMetrics metrics = graphics.getFontMetrics(font);
                int x = (SCREEN_WIDTH - metrics.stringWidth(displayText)) / 2;
                int y = ((SCREEN_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
                graphics.setFont(font);
                graphics.drawString(displayText, x, y - 60);
            }

            graphics.setColor(Color.WHITE);
            String displayText = "Press \"Space\" to begin!";
            Font font = new Font(null, 1, 50);

            FontMetrics metrics = graphics.getFontMetrics(font);
            int x = (SCREEN_WIDTH - metrics.stringWidth(displayText)) / 2;
            int y = ((SCREEN_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
            graphics.setFont(font);
            graphics.drawString(displayText, x, y);

            return;
        }
        attemptMove();
        displaySnake();
        displayApple();
//        displayBoarder();
    }

    public void attemptMove() {
        Location nextTile = determineNextTile();

        // game over
        if (nextTile.hasSnakePart() || nextTile.isOutOfBounds()) {
            ALIVE = false;
            return;
        }
        // eat apple
        if (nextTile.equals(APPLE_LOCATION)) {
            eat();
        }
        // delete last body part, add new Tile as first index (move)
        else {
            SNAKE.removeLast();
            SNAKE.addFirst(nextTile);
        }
    }

    public void eat() {
        SNAKE.addFirst(APPLE_LOCATION);
        spawnApple();
        if (SNAKE.size() <= 25 && SNAKE.size() % 5 == 0) {
            DELAY -= 5;
        }
    }

    public Location determineNextTile() {
        Location head = SNAKE.get(0);
        switch (DIRECTION) {
            case 'w':
                return new Location(head.getX(), head.getY() - 1);
            case 'a':
                return new Location(head.getX() - 1, head.getY());
            case 's':
                return new Location(head.getX(), head.getY() + 1);
            case 'd':
                return new Location(head.getX() + 1, head.getY());
        }
        return null;
    }

    public void spawnApple() {
        APPLE_LOCATION = new Location();
    }

    public void spawnSnake() {
        SNAKE = new LinkedList<>();
        int[] bodyLocation = {Location.MAX_X / 2, Location.MAX_Y / 2};
        Location bodyPart;
        for (int i = 0; i < 3; i++) {
            bodyPart = new Location(bodyLocation[0], bodyLocation[1] + i);
            SNAKE.add(bodyPart);
        }
    }

    public void displaySnake() {
        for (Location part : SNAKE) {
            drawObject(part, "snake");
        }
    }

    public void displayBoarder() {
        graphics.setColor(Color.darkGray);
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
        }
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            graphics.drawLine(0, UNIT_SIZE * i, SCREEN_WIDTH, UNIT_SIZE * i);
        }
    }

    public void displayApple() {
        if (APPLE_LOCATION == null) {
            spawnApple();
        }
        drawObject(APPLE_LOCATION, "apple");
    }

    public void drawObject(Location location, String shape) {
        if (location.isOutOfBounds()) {
            return;
        }
        if (shape.equals("snake")) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect(location.getX() * UNIT_SIZE, location.getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        } else if (shape.equals("apple")) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect(location.getX() * UNIT_SIZE + (int) (UNIT_SIZE / 2.25), location.getY() * UNIT_SIZE + (int) (UNIT_SIZE / 2.25), (int) (UNIT_SIZE / 2.25), (int) (UNIT_SIZE / 2.25));
            graphics.setColor(Color.RED);
            graphics.fillOval(location.getX() * UNIT_SIZE, location.getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }
    }

    public class KeyAdapter extends java.awt.event.KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyChar() == 'w' || event.getKeyChar() == 'a' || event.getKeyChar() == 's' || event.getKeyChar() == 'd') {
                char oldDirection = DIRECTION;
                SnakeGame.DIRECTION = event.getKeyChar();
                if (determineNextTile().equals(SNAKE.get(1))) {
                    DIRECTION = oldDirection;
                }
            } else if (event.getKeyChar() == KeyEvent.VK_SPACE) {
                if (!ALIVE) {
                    STARTUP = true;
                }
            }
        }
    }

}
