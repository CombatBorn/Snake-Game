package testing.Snake;

// https://youtu.be/bI6e6qjJ8JQ?t=1023

import java.util.LinkedList;

public class SnakeGame {

    // game settings
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;
    public static final int UNIT_SIZE = 20;
    public static int DELAY;

    // user interface
    public static GameGraphics GAME_GRAPHICS;
    public static GameFrame GAME_FRAME;


    // game graphics
    public static Location APPLE_LOCATION;
    // The first key represents the head, score = snake size - 3
    public static LinkedList<Location> SNAKE;

    // game variables
    // DIRECTION = w, a, s, or d
    public static char DIRECTION;
    public static boolean ALIVE;
    public static boolean STARTUP;

    public static void main(String[] args) throws InterruptedException {
        // setup user interface
        GAME_GRAPHICS = new GameGraphics();
        GAME_FRAME = new GameFrame();

        // start game
        SnakeGame.start();
    }

    public static void start() throws InterruptedException {
        System.out.println("Let the games begin!");

        // variables
        ALIVE = false;
        DELAY = 75;
        STARTUP = false;

        // draw start message
        GAME_GRAPHICS.repaint();

        // game loop
        while (true) {
            Thread.sleep(DELAY);

            // setup game
            if (!ALIVE && STARTUP) {
                ALIVE = true;
                STARTUP = false;
                GAME_GRAPHICS.spawnApple();
                GAME_GRAPHICS.spawnSnake();
                DIRECTION = 'w';
            }
            // draw frame
            else{
                GAME_GRAPHICS.repaint();
            }
        }

    }
}
