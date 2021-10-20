package battlecity;

public class MyTank extends Tank {
    Bullet bullet = null;

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void ShotBullet() {
        switch (getDirection()) {
            case 0:
                bullet = new Bullet(getX() + 20, getY(), 0);
                break;
            case 1:
                bullet = new Bullet(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                bullet = new Bullet(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                bullet = new Bullet(getX(), getY() + 20, 3);
                break;
        }
        new Thread(bullet).start();
    }

}
