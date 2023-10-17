
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    // TODO: declare game variables

    Platform platform;
    Ball ball;
    ArrayList<Brick> bricks;

    public void settings() {
        size(800, 600);   // set the window size

    }
    public void setup() {
        platform = new Platform(width / 2, 500, 85, 20, 5);

        bricks = new ArrayList<>();
        for (int i = 0; i < width; i += 50) {
            for (int j = 0; j < 80; j += 20) {
                bricks.add(new Brick(i, j, 20, 50));
            }
        }
        ball = new Ball(500,300,1,1, 15);
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        if (keyPressed) {
            if (keyCode == RIGHT || keyCode == 'd') {
                platform.moveRight(this);
            }
            if (keyCode == LEFT || keyCode == 'a') {
                platform.moveLeft(this);
            }
        }

        background(255);    // paint screen white

        platform.draw(this);
        for (Brick brick : bricks) {
            brick.draw(this);
            brick.collide(ball);
        }

        ball.draw(this);
        ball.collision(platform, this);
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}

