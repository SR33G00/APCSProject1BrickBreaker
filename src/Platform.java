import processing.core.PApplet;

public class Platform {
    public int x, xSpeed, y, width, height;

    public Platform(int x, int y, int width, int height, int xSpeed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
    }

    public void moveRight(PApplet window) {
        if (this.x + width <= window.width) {
            this.x += xSpeed;
        }
    }

    public void moveLeft(PApplet window) {
        if(this.x >= 0) {
            this.x -= xSpeed;
        }   
    }

    public void draw(PApplet window) {
        window.fill(255, 0, 0);
            window.rect(this.x, this.y, this.width, this.height);

    }

}
