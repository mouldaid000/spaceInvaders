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

    ArrayList<Entity> aliens;
    ArrayList<Entity> ship;
    ArrayList<Entity> bullets;

    int alienCount;

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
            if(alienCount == 0){
                Stats.endGame();
            }
            ship.get(0).move();
            for(int i = 0; i < aliens.size(); i++){
                aliens.get(i).move();
            }
            for(int i = 0; i < bullets.size(); i++){
                bullets.get(i).move();
            }
            if(Stats.isEnd()){
                if(spacePressed){
                    System.exit(0);
                }
           }
        }

        repaint();
    }
    public void init(){
        aliens = new ArrayList<Entity>();
        ship = new ArrayList<Entity>();
        bullets = new ArrayList<Entity>();
        ship.add(new Ship(Color.BLUE, getWidth()/2,  6,getHeight()-75, 42, 36, this, 0));
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 10; j++){
                aliens.add(new Alien(Color.getHSBColor(0.0f, 1.0f, .58f),(j*75)+15,4,(i*50)+15, 25, 15, this, aliens.size()));
                alienCount = 60;
            }
        }
    }
    public void run(){
        timer = new Timer(1000/60, this);
        timer.start();
    }
    public void collisions(){
        for(int i = 0; i < aliens.size(); i++){
            for(int j = 0; j < bullets.size(); j++){
                if(aliens.get(i).collides(bullets.get(j))){
                    if(bullets.get(j) instanceof Bullet) {
                        bullets.remove(j);
                        aliens.remove(i);
                        Stats.addScore();
                        alienCount--;
                    }
                }
            }
        }
        for(int i = 0; i < aliens.size(); i++){
            if(aliens.get(i).collides(ship.get(0))){
                if(ship.get(0) instanceof Ship){
                    Stats.endGame();
                }
            }
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
            for(Entity alien : aliens){
                alien.paint(g);
            }
            ship.get(0).paint(g);
            for(Entity bullet : bullets){
                bullet.paint(g);
            }
            g.setColor(new Color(8,160,255));
            g.setFont(new Font("segoe ui", Font.PLAIN, 24));
            printSimpleString("Score:"+Stats.score, getWidth(), -(1024/2)+150,getHeight()-25,g);
        }
        if (Stats.isPause()) {

            g.setFont(new Font("segoe ui", Font.BOLD, 72));
            printSimpleString("PAUSED", getWidth(), 0, getHeight()/2,g);

        }
        if(Stats.isEnd()){
            g.setColor(new Color(8, 160,255));
            g.setFont(new Font("seqoe ui", Font.PLAIN, 64));
            printSimpleString("Game Over", getWidth(), 0, (getHeight()/2)-25, g);
            g.setFont(new Font("segoe ui", Font.PLAIN, 48));
            printSimpleString("You scored: " + Stats.score, getWidth(), 0, (getHeight()/2)+25, g);
            printSimpleString("Press [SPACE] to End", getWidth(), 0, getHeight() - 25,g);
        }
    }
    //Separate methods for making my title screen look sexy
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
    public void removeAlien(int index){
        aliens.remove(index);
    }

    //Bullet methods
    public void addBullet(Bullet b) {
            bullets.add(b);
    }
    public void removeBullet(int index){
        bullets.remove(index);
    }
    public int getNextBullet(){
        return bullets.size();
    }

    //String printing
    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d) {
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s, start + XPos, YPos);
    }

    //Boolean getters
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

