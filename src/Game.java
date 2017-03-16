import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Aidan Moulder on 3/15/2017.
 */
public class Game extends JPanel implements ActionListener {

    static Timer timer;

    boolean aPressed, dPressed, spacePressed, pPressed, mouse1Pressed;

    ArrayList<Alien> aliens;
    ArrayList<Ship> ship;
    ArrayList<Bullet> bullets;

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
    }

    public Game() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Space Invaders");
        setPreferredSize(new Dimension(1024, 768));
        setBackground(Color.BLACK);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //unused
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_A) {
                    aPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    dPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = true;
                    Stats.startPlay();
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    pPressed = true;
                    Stats.togglePause();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    aPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    dPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = false;

                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    pPressed = false;

                }
            }
        });
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                super.mouseClicked(e);
            }
            @Override
            public void mousePressed(MouseEvent e){
                super.mousePressed(e);
                if(e.getButton() == MouseEvent.BUTTON1){
                    mouse1Pressed = true;
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                super.mouseReleased(e);
                if(e.getButton() == MouseEvent.BUTTON1){
                    mouse1Pressed = false;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Stats.isPlay()) {
            collisions();
            ship.get(0).move();
            for(int i = 0; i < aliens.size(); i++){
                aliens.get(i).move();
            }
            for(int i = 0; i < bullets.size(); i++){
                bullets.get(i).move();
            }
            //if(Stats.isEnd()){
                //if(isSpace()){
                    //resetGame();
                //}
           //}
        }

        repaint();
    }
    public void init(){
        aliens = new ArrayList<Alien>();
        ship = new ArrayList<Ship>();
        bullets = new ArrayList<Bullet>();
        ship.add(new Ship(Color.BLUE, getWidth()/2,  6,getHeight()-75, 42, 36, this, 0));
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 10; j++){
                aliens.add(new Alien(Color.getHSBColor(0.0f, 1.0f, .58f),(j*50)+15,3,(i*50)+15, 20, 10, this, aliens.size()));
            }
        }
    }
    public void run(){
        timer = new Timer(1000/60, this);
        timer.start();
    }
    public void collisions(){
        for(Ship s : ship){
            s.checkCollision();
        }
        for(Bullet b : bullets){
            b.checkCollision();
        }
        for(Alien a : aliens){
            a.checkCollision();
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        if (Stats.isMenu()) {

            g.setFont(new Font("segoe ui", Font.BOLD, 256));
            g.setColor(Color.WHITE);
            printSimpleString("Space", getWidth(), -2, (getHeight() / 2) + 2, g);
            printSimpleString("Space", getWidth(), +2, (getHeight() / 2) - 2, g);
            g.setFont(new Font("segoe ui", Font.BOLD, 256));
            g.setColor(Color.BLACK);
            printSimpleString("Space", getWidth(), 0, (getHeight() / 2), g);
            g.setFont(new Font("segoe ui", Font.PLAIN, 72));
            g.setColor(Color.WHITE);
            printSimpleString("Invaders", getWidth(), -1, (getHeight() / 2) + 59, g);
            printSimpleString("Invaders", getWidth(), +1, (getHeight()/2)+61, g);
            g.setColor(Color.getHSBColor(.79f, 1.0f, .65f));
            printSimpleString("Invaders", getWidth(), 0,(getHeight()/2)+60, g);
            g.setColor(Color.WHITE);

            g.setFont(new Font("segoe ui", Font.ITALIC, 54));
            printSimpleString("Press [SPACE] to Play", getWidth(), -1, (getHeight()/2) + 149, g);
            printSimpleString("Press [SPACE] to Play", getWidth(), +1, (getHeight()/2) + 151, g);
            g.setColor(Color.getHSBColor(.79f, 1.0f, .65f));
            printSimpleString("Press [SPACE] to Play", getWidth(), 0, (getHeight()/2) + 150, g);
            g.setColor(Color.WHITE);
            for(int i = 0; i <= 10; i++){
                g.fillRect(randomX(), randomY(), 3, 3);
            }
        }
        if(Stats.isPlay()){
            for(Alien a : aliens){
                a.paint(g);
            }
            ship.get(0).paint(g);
            for(Bullet b : bullets){
                b.paint(g);
            }
        }
    }
    public int randomX(){
        int max = 1023, min = 1;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;

    }
    public int randomY(){
        int max = 767, min = 1;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min)+ 1) + min;
        return randomNum;
    }

    //Alien methods

    public void addAlien(Alien a){
        aliens.add(a);
    }
    public Alien getAlien(int index){
        return aliens.get(index);
    }
    public void removeAlien(int index){
        aliens.remove(index);
        for(int i = index; i < aliens.size(); i++){
            aliens.get(i).decrementIndex();
        }
    }
    public int getNextAlien(){
        return aliens.size();
    }

    //Bullet methods
    public void addBullet(Bullet b) {
            bullets.add(b);
    }
    public Bullet getBullet(int index){
        return bullets.get(index);
    }
    public void removeBullet(int index){
        bullets.remove(index);
        for(int i = index; i < bullets.size(); i++){
            bullets.get(i).decrementIndex();
        }
    }
    public int getNextBullet(){
        return bullets.size();
    }


    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d) {
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos);
    }

    public boolean isA(){
        return aPressed;
    }
    public boolean isD(){
        return dPressed;
    }
    public boolean isClick(){
        return mouse1Pressed;
    }
}

