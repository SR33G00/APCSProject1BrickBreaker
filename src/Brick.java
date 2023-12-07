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
        if(ball.y - ball.radius/2 <= this.y + this.height) {
            if ((ball.x) >= this.x && ball.x <= this.x + this.width) {
                removeBrick();
                ball.ySpeed = ball.ySpeed * -1;
            }
        }

        if(ball.y + ball.radius >= this.y && ball.y + ball.y <= this.y + height){
            if(ball.x + ball.radius >= this.x && ball.x - ball.radius <= this.x){
                removeBrick();
                ball.xSpeed = ball.xSpeed * -1;
            }
        }

        if(ball.y >= this.y && ball.y <= this.y + height){
            if(ball.x + ball.radius >= this.x && ball.x - ball.radius <= this.x){
                removeBrick();
                ball.xSpeed = ball.xSpeed * -1;
            }
        }
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void removeBrick(){
        this.width = 0;
        this.height = 0;
        this.y = -100000;
        this.x = -100000;
    }
}
