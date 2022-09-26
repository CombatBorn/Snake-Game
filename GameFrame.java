package testing.Snake;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        this.add(SnakeGame.GAME_GRAPHICS);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
