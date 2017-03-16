import java.awt.*;

/**
 * Created by Aidan Moulder on 3/15/2017.
 */
public class Bullet extends Entity {

    private int bulletTimer;

    public Bullet(Color color, int x, int dx, int y, int width, int height, Game game, int index) {
        super(color, x, dx, y, width, height, game, index);
        bulletTimer = 300;

    }

    @Override
    public void move() {
        setY(getY() - getDx());
        bulletTimer--;
        if(bulletTimer <= 0){
            getGame().removeBullet(getIndex());
        }
    }

    @Override
    public void paint(Graphics g) {

            g.setColor(Color.ORANGE);
            g.fillRect(getX() - getWidth() / 2, getY(), getWidth(), getHeight());

    }

    @Override
    public void kill() {

    }

    @Override
    public void checkCollision() {

    }
}
