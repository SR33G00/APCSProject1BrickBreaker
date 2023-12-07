
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    // TODO: declare game variables

    Platform platform;
    Ball ball;
    ArrayList<Brick> bricks;

    boolean pause = false;

    private double ballPauseXspeed;
    private double ballPauseYspeed;
    private int platformPauseXspeed;

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
        ball = new Ball(400, 300, 1, 3, 15);
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
        ball.draw(this, bricks);
        ball.collision(platform, this);

        this.color((200));
        this.textSize(12);
        text("Press 'p' to pause", width - 100, height - 20);


        if (pause == true) {
            this.textSize(30);
            this.text("Paused", this.width / 2 - 50, this.height / 2);

        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    @Override
    public void keyReleased() {
        super.keyReleased();
        if (key == 'p') {
            GamePause(ball.xSpeed, ball.ySpeed, platform.xSpeed);
        }
    }

    private void GamePause(double xSpeed, double ySpeed, int platformSpeed) {
        if (xSpeed == 0 && ySpeed == 0) {
            ball.xSpeed = ballPauseXspeed;
            ball.ySpeed = ballPauseYspeed;
            platform.xSpeed = platformPauseXspeed;
            pause = false;

        } else {

            pause = true;
            platformPauseXspeed = platformSpeed;
            ballPauseXspeed = xSpeed;
            ballPauseYspeed = ySpeed;
            platform.xSpeed = 0;
            ball.ySpeed = 0;
            ball.xSpeed = 0;
        }
    }
}

