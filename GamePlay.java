import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    public boolean play = false;
    private int score = 0;
    private final Timer timer;
    private final int delay = 8;
    private int playerX = 310;
    private int playerY = 300;
    private final int speed = 18;
    private int ballX = 120;
    private int ballY = 350;
    private int ballXdir = -5;
    private int ballYdir = -5;
    private int ballX2 = 320;
    private int ballY2 = 350;
    private int ballXdir2 =-4;
    private int ballYdir2 =-4;
    private int ballX3 = 150;
    private int ballY3 = 250;
    private int ballXdir3 =-5;
    private int ballYdir3 =-5;
    private int ballX4 = 550;
    private int ballY4 = 250;
    private int ballXdir4 =-6;
    private int ballYdir4 =-6;
    private final JFrame frame;
    private BufferedImage playerImage;
    private MainMenu menu;
    Sound sound = new Sound();
    Sound die = new Sound();
    Sound win = new Sound();
    Mouseinput mi = new Mouseinput();
    MainMenu mm = new MainMenu();


    public enum STATE{
        MENU , PLAY
    }
   public static STATE State = STATE.MENU;
    public GamePlay(JFrame frame) {
        this.frame = frame;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.BLACK);
        this.addMouseMotionListener(new Mouseinput());
        this.addMouseListener(new Mouseinput());

        timer = new Timer(delay, this);
        timer.start();
        sound.setFile(0);
        sound.play();
        sound.loop();


        try {
            // Load the player image
            playerImage = ImageIO.read(getClass().getResourceAsStream("/res/ballre.png")); // Path to your player image
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading player image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (State == STATE.PLAY) {
            renderPlay(g);
        } else if (State == STATE.MENU) {
            renderMenu(g);
        }
        repaint();
    }

    private void renderPlay(Graphics g) {
        if(State == STATE.PLAY) {
            g.setColor(Color.BLACK);
            g.fillRect(1, 1, 692, 592);

            // Draw the border
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0, 0, 692, 3);
            g.fillRect(691, 0, 3, 592);
            g.fillRect(0, 571, 700, 3);

////            //find player hitbox
//            g.setColor(Color.CYAN);
//            g.drawRect(playerX+5,playerY+5,30,30);

//            //find ball hitbox
//            g.setColor(Color.CYAN);
//            g.drawRect(ballX,ballY,20,20);
//            g.drawRect(ballX2,ballY2,20,20);
//            g.drawRect(ballX3,ballY3,25,25);
//            g.drawRect(ballX4+4,ballY4+4,26,26);

            // Draw the ball 1
            g.setColor(Color.GREEN);
            g.fillOval(ballX, ballY, 20, 20);

            //Draw ball 2
            if(score > 10) {
                g.setColor(Color.GREEN);
                g.fillOval(ballX2, ballY2, 20, 20);
            }
            if(score >20){
                g.setColor(Color.RED);
                g.fillOval(ballX3, ballY3, 25, 25);
            }
            if(score >29){
                g.setColor(Color.yellow);
                g.fillOval(ballX4, ballY4, 35, 35);
            }
            //for score text
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial",Font.BOLD,20));
            g.drawString("Score: "+score,550,30);

            // Draw the player image
            if (playerImage != null) {

                int scaledWidth = playerImage.getWidth();
                int scaledHeight = playerImage.getHeight();
                g.drawImage(playerImage, playerX, playerY, scaledWidth, scaledHeight, null);
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (State == STATE.PLAY) {
            timer.start();
            ballX += ballXdir;
            ballY += ballYdir;

            // Check for collision with walls
            if (ballX < 0 || ballX > 670) {
                ballXdir = -ballXdir;
                ++score;
            }
            if (ballY < 0 || ballY > 545) {
                ballYdir = -ballYdir;
                ++score;
            }

            if (score > 10) {
                ballX2 += ballXdir2;
                ballY2 += ballYdir2;
                // Check boundaries for the second ball's movement
                if (ballX2 < 0 || ballX2 > 670) {
                    ballXdir2 = -ballXdir2;
                }
                if (ballY2 < 0 || ballY2 > 545) {
                    ballYdir2 = -ballYdir2;
                }
            }
            if (score > 20) {
                ballX3 += ballXdir3;
                ballY3 += ballYdir3;
                // Check boundaries for the third ball's movement
                if (ballX3 < 15 || ballX3 > 660) {
                    ballXdir3 = -ballXdir3;
                }
                if (ballY3 < 30 || ballY3 > 530) {  // Corrected ballY2 to ballY3
                    ballYdir3 = -ballYdir3;
                }
            }
            if(score >29){
                ballX4 += ballXdir4;
                ballY4 += ballYdir4;
                // Check boundaries for the third ball's movement
                if (ballX4 < 15 || ballX4 > 660) {
                    ballXdir4 = -ballXdir4;
                }
                if (ballY4 < 30 || ballY4 > 530) {  // Corrected ballY2 to ballY3
                    ballYdir4 = -ballYdir4;
                }
            }

            Rectangle playerRectangle = new Rectangle(playerX+5,playerY+5,30,30);
            // Check for collision with player
            if (new Rectangle(ballX, ballY, 15, 15).intersects(playerRectangle)
                    || (score > 11 && new Rectangle(ballX2, ballY2, 15, 15).intersects(playerRectangle))
                    || (score > 21 &&new Rectangle(ballX3, ballY3, 20, 20).intersects(playerRectangle))
                    ||(score > 29 &&new Rectangle(ballX4+4,ballY4+4,26,26).intersects(playerRectangle))) {
                die.setFile(1);
                die.play();
                sound.stop();
                play = false; // Game over
                timer.stop(); // Stop the timer
                ImageIcon icon = new ImageIcon(getClass().getResource("/res/nice30.png"));
                JOptionPane.showMessageDialog(this, "Game Over Try Again!! Your score: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE,icon);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // Close the window
            }
            if(score >= 40){
                win.setFile(4);
                win.play();
                sound.stop();


                play = false; // Win
                timer.stop();

                ImageIcon icon = new ImageIcon(getClass().getResource("/res/medalRe.png"));
                JOptionPane.showMessageDialog(this, "Your'e Winner!!", "Game Over", JOptionPane.INFORMATION_MESSAGE,icon);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

                if(State == STATE.MENU){
                    timer.start();
                }
            }

            repaint();
        }
    }





    private void renderMenu(Graphics g) {
        menu = new MainMenu();
        menu.render(g);


        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(State == STATE.PLAY){
        if (e.getKeyCode() == KeyEvent.VK_A||e.getKeyCode()== KeyEvent.VK_LEFT) {
            if (playerX <= 4) {
                playerX = 4;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D||e.getKeyCode()== KeyEvent.VK_RIGHT) {
            if (playerX >= 634) {
                playerX = 647;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W||e.getKeyCode()== KeyEvent.VK_UP) {
            if (playerY <= 20) {
                playerY = 4;
            } else {
                moveUp();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S||e.getKeyCode()== KeyEvent.VK_DOWN) {
            if (playerY >= 515) {
                playerY = 530;
            } else {
                moveDown();
            }
        }
    }
    }

    public void moveLeft() {
        play = true;
        playerX -= speed;
    }

    public void moveRight() {
        play = true;
        playerX += speed;
    }

    public void moveUp() {
        play = true;
        playerY -= speed;
    }

    public void moveDown() {
        play = true;
        playerY += speed;
    }



    @Override
    public void keyReleased(KeyEvent e) {
    }



//    class Menu {
//
//        public Rectangle playButton = new Rectangle(300,300,120,70);
//        public Rectangle exitButton = new Rectangle(185,400,350,70);
//        public Rectangle bo = new Rectangle(185,400,350,70);
//        public void render(Graphics g){
//            Graphics2D g2 =(Graphics2D) g;
//
//
//            Font fnt0 = new Font("arial",Font.BOLD,50);
//            g.setFont(fnt0);
//            g.setColor(Color.WHITE);
//            g.drawString("Dodge it if you can!",130,200);
//            Font fnt1 = new Font("arial",Font.BOLD,30);
//            g.setFont(fnt1);
//            g.drawString("Let's try",playButton.x,playButton.y+45);
//            g.drawString("I wanna Exit i'm noob",playButton.x-90,playButton.y+145);
//
//            g2.draw(playButton);
//            g2.draw(exitButton);
//
//
//        }
//    }



}
