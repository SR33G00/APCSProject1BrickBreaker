
import processing.core.PApplet;
import processing.core.PImage;

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

    PImage ChristmasOrnament = new PImage();
    PImage BrickImage = new PImage();
    PImage PlatformImage = new PImage();
    PImage backgroundImage = new PImage();
    PImage WinningScreen = new PImage();
    PImage GameOver;

    public void settings() {
        size(800, 550);   // set the window size
    }

    public void setup() {

        WinningScreen = loadImage("WinningScreen.jpg");
        GameOver = loadImage("GameOver.jpg");
        platform = new Platform(width / 2, 500, 85, 20, 5);

        bricks = new ArrayList<>();
        for (int i = 0; i < width; i += 50) {
            for (int j = 0; j < 120; j += 30) {
                bricks.add(new Brick(i, j, 25, 50));
            }
        }
        ball = new Ball(this, 400, 300, 1, 4, 20);


        PlatformImage = loadImage("PlatformImage.png");
        PlatformImage.resize(platform.width, platform.height);
        BrickImage = loadImage("BrickImage.jpg");
        BrickImage.resize(50, 30);
        ChristmasOrnament = loadImage("BallOrnament.png");
        ChristmasOrnament.resize((int) (ball.radius * 2) - 5 - 5, (int) (ball.radius * 2) - 5);
        backgroundImage = loadImage("BrickBreakerBackground.png");
        backgroundImage.resize(this.width, this.height);
        WinningScreen = loadImage("WinningScreen.jpg");
        WinningScreen.resize(this.width, this.height);
        GameOver = loadImage("GameOver.jpg");
        GameOver.resize(this.width, this.height);
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        if(ball.GameWon){
            background(WinningScreen);
        }else if (ball.GameEnd) {
            background(GameOver);
        }else{
            background(backgroundImage);
        }

        if (keyPressed) {
            if (keyCode == RIGHT || keyCode == 'd') {
                platform.moveRight(this);
            }
            if (keyCode == LEFT || keyCode == 'a') {
                platform.moveLeft(this);
            }
        }

        platform.draw(this);
        image(ChristmasOrnament, (float) (ball.x - ball.radius), (float) (ball.y - ball.radius));
        for (Brick brick : bricks) {
            brick.draw(this);
            image(BrickImage, brick.getX(), brick.getY());
            brick.collide(ball);
        }
        ball.draw(this, bricks, platform);
        image(PlatformImage, platform.x, platform.y);
        ball.collision(platform, this, bricks);


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

