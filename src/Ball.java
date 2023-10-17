import processing.core.PApplet;

public class Ball {
    public int x, y, xSpeed, ySpeed, radius;

    public Ball(int x, int y, int xSpeed, int ySpeed, int radius){
        this. x = x;
        this. y = y;
        this.radius = radius;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void draw(PApplet window){
        this.x += xSpeed;
        this.y += ySpeed;
        window.fill(255,0,0);
        window.ellipse(this.x,this.y,this.radius,this.radius);
        }
    public void collision(Platform platform, PApplet window){
        //Colliding with platform
        if(this.y + this.radius/2 == platform.y) {
            if ((this.x - this.radius/2) >= platform.x && this.x + this.radius/2 <= platform.x + platform.width) {
                this.ySpeed = this.ySpeed * -1;
            }
        }
        //Colliding with walls
        if(this.x - this.radius/2 <= 0 || this.x + this.radius/2 >= window.width){
            this.xSpeed = this.xSpeed * -1;
        }
        if(this.y + this.radius > window.height){
            window.background(0);
            window.text("GAME OVER", window.width/2, window.height/2);
        }
    }
}
