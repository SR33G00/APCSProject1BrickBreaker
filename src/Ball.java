import processing.core.PApplet;

import java.util.ArrayList;

public class Ball {
    double x, y, xSpeed, ySpeed, radius;

    public Ball(double x, double y, double xSpeed, double ySpeed, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void draw(PApplet window, ArrayList<Brick> bricks) {
        this.x += xSpeed;
        this.y += ySpeed;
        window.fill(255, 0, 0);
        window.ellipse((float) this.x, (float) this.y, (float) this.radius, (float) this.radius);

        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).getX() >= -100) {
                break;
            } else if (i == bricks.size() - 1) {
                xSpeed = 0;
                ySpeed = 0;
                window.background(0);
                window.textSize(20);
                window.text("CONGRATULATIONS, YOU WON THE GAME", (float) window.width / 2 - 200, (float) window.height / 2);
            }
        }
    }

    public void collision(Platform platform, PApplet window) {
        //Colliding with platform
        if (this.y + this.radius / 2 >= platform.y) {
            if ((this.x) >= platform.x && this.x <= platform.x + platform.width) {
                this.ySpeed = this.ySpeed * 1.001;
                this.ySpeed = this.ySpeed * -1;
                this.xSpeed = randomXSpeed();
            }
        }
/*
        if (this.x + this.radius >= platform.x || this.x - this.radius <= platform.x + platform.width) {
            if (this.y <= platform.y && this.y >= platform.y + platform.height) {
                xSpeed = xSpeed * -1;
            }
        }
 */
        if (this.y - this.radius / 2 <= 0) {
            this.ySpeed = ySpeed * -1;
        }
        //Colliding with walls

        if (this.x - this.radius <= 0 || this.x + this.radius / 2 >= window.width) {
            this.xSpeed = this.xSpeed * -1;
        }

        if (this.y - this.radius <= 0) {
            ySpeed = -ySpeed;
        }

        if (this.y + this.radius > window.height) {
            window.background(0);
            window.textSize(20);
            window.text("GAME OVER", (window.width / 2) - 50, window.height / 2);
            this.xSpeed = 0;
            this.ySpeed = 0;
        }

    }

    public double randomXSpeed() {
        return (Math.random() - 0.5) * 10;
    }
}
