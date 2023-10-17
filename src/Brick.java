import processing.core.PApplet;

public class Brick {
    private int x, y, width, height;
    boolean isAlive;

    public Brick(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.isAlive = true;
    }
    public boolean isAlive(){
     return isAlive;

    }
    public void draw(PApplet window){
        window.fill(255,0,0);
        window.rect(this.x, this.y, this.width, this.height);
    }
    public void collide(Ball ball){
        //collision with bottom of the brick
        if(ball.y + ball.radius == this.y + this.height) {
            if ((ball.x - ball.radius) >= this.x && ball.x + ball.radius <= this.x + this.width) {
                removeBrick();
                ball.ySpeed = ball.ySpeed * -1;
            }
        }
    }
    public void removeBrick(){
        this.width = 0;
        this.height = 0;
    }
}
