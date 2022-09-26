package testing.Snake;

import static testing.Snake.SnakeGame.*;

public class Location {

    public static final int MAX_X = SCREEN_WIDTH / UNIT_SIZE - 1;
    public static final int MAX_Y = SCREEN_HEIGHT / UNIT_SIZE - 1;

    private final int x;
    private final int y;

    // generates a random location
    // -1 will ensure x and y account for the 0th index
    public Location() {
        this.x = (int) (MAX_X * Math.random());
        this.y = (int) (MAX_Y * Math.random());
    }

    // tiles start at location x:1, y:1 on the top left of the grid
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOutOfBounds(){
        return this.x < 0 || this.y < 0 || this.x > MAX_X || this.y > MAX_Y;
    }

    public boolean hasSnakePart(){
        for (Location part : SNAKE){
            if (this.equals(part)){
                return true;
            }
        }
        return false;
    }

    public boolean equals(Location location){
        return this.x == location.getX() && this.y == location.getY();
    }

    @Override
    public String toString() {
        return "X:" + this.x + ", Y:" + this.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
