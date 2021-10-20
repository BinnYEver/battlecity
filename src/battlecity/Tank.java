package battlecity;

public class Tank {
    private int x;
    private int y;
    private int direction;
    private int speed = 1;

    public int getDirection() {
        return direction;
    }

    public void MoveUp() {
        y -= speed;
    }

    public void MoveRight() {
        x += speed;
    }

    public void MoveDown() {
        y += speed;
    }

    public void MoveLeft() {
        x -= speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
