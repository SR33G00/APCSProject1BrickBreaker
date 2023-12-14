import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Ball {
    double x, y, xSpeed, ySpeed, radius;

    PImage WinningScreen = new PImage();
    PImage GameOver = new PImage();

    boolean GameEnd = false;
    boolean GameWon = false;


    public Ball(PApplet window, double x, double y, double xSpeed, double ySpeed, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        GameOver = window.loadImage("GameOver.jpg");
        WinningScreen = window.loadImage("WinningScreen.jpg");
    }

    public void draw(PApplet window, ArrayList<Brick> bricks, Platform platform) {

        this.x += xSpeed;
        this.y += ySpeed;
        window.fill(255, 0, 0);
       // window.ellipse((float) this.x, (float) this.y, (float) this.radius, (float) this.radius);

        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).getX() >= -100) {
                break;
            } else if (i == bricks.size() - 1) {
                xSpeed = 0;
                ySpeed = 0;
                GameWon = true;
                bricks.clear();
                this.x = -100000;
                platform.x = -10000;
               // window.background(0);
               // window.textSize(20);
               // window.text("CONGRATULATIONS, YOU WON THE GAME", (float) window.width / 2 - 200, (float) window.height / 2);
            }
        }
    }

    public void collision(Platform platform, PApplet window, ArrayList<Brick> bricks) {
        //Colliding with platform
        if (this.y + this.radius / 2 >= platform.y) {
            if ((this.x) >= platform.x && this.x <= platform.x + platform.width) {
                this.ySpeed = this.ySpeed * 1.001;
                this.ySpeed = this.ySpeed * -1;
                this.xSpeed = randomXSpeed();
            }
        }

        //Colliding with walls

        if (this.y - this.radius / 2 <= 0) {
            this.ySpeed = ySpeed * -1;
        }

        if (this.x - this.radius <= 0 || this.x + this.radius / 2 >= window.width) {
            this.xSpeed = this.xSpeed * -1;
        }

        if (this.y - this.radius <= 0) {
            ySpeed = -ySpeed;
        }

        //GAME OVER
        if (this.y + this.radius >= window.height) {
            GameEnd = true;
            bricks.clear();
            this.x = -100000;
            platform.x = -10000;
          //  window.background(0);
          //  window.textSize(20);
          //  window.text("GAME OVER", (window.width / 2) - 50, window.height / 2);
            this.xSpeed = 0;
            this.ySpeed = 0;
        }

    }

    public double randomXSpeed() {
        return (Math.random() - 0.5) * 10;
    }
}
