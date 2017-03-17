import java.awt.*;

/**
 * Created by Aidan Moulder on 3/15/2017.
 */
public class Ship extends Entity {
    boolean visible = true, bulletFired = false;
    int bulletTimer;

    public Ship(Color color, int x, int dx, int y, int width, int height, Game game, int index) {

        super(color, x, dx ,y, width, height, game, index);
        bulletTimer = 12;
    }

    @Override
    public void move() {

        int nextLeft = getX() - getDx();
        int nextRight = getX() + getDx();


        if(getGame().isA()){
            if(nextLeft < 0){}
            else setX(getX()-getDx());
        }



        if(getGame().isD()){
            if(nextRight > getGame().getWidth() - this.getWidth()){}
            else setX(getX()+getDx());
        }

        if(getGame().isClick() && !bulletFired && bulletTimer == 0){

            bulletFired = true;
            getGame().addBullet(new Bullet(Color.yellow, getX() + (getWidth()) / 2, 7,getY() + (getHeight()) / 2, 10, 10, getGame(), getGame().getNextBullet()));


        }

        if(!getGame().isClick()) {
            bulletFired = false;
        }
        bulletTimer--;
        if(bulletTimer <0){
            bulletTimer = 12;
        }

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void kill() {
        Stats.endGame();
    }

}
