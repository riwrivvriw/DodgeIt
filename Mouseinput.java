import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouseinput implements MouseListener ,MouseMotionListener{

    Sound enter = new Sound();
    Sound mouse = new Sound();

    Rectangle playButton = new Rectangle(300, 300, 120, 70);
    Rectangle exitButton = new Rectangle(185, 400, 350, 70);
    public boolean border = false;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        public Rectangle playButton = new Rectangle(300,300,120,70);
        if (GamePlay.State == GamePlay.STATE.MENU) {
            int mx = e.getX();
            int my = e.getY();

            //for play
            if (mx >= 300 && mx <= 420) {
                if (my >= 300 && my <= 370) {
                    System.out.println("HI im working");
                    enter.setFile(2);
                    enter.play();
                    GamePlay.State = GamePlay.STATE.PLAY;
                }
            }
            //for exit
            if (mx >= 185 && mx <= 535) {
                if (my >= 400 && my <= 470) {
                    enter.setFile(2);
                    enter.play();
                    System.exit(1);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    // Declare boolean flags to track whether sounds have been played
    public boolean playButtonSoundPlayed = false;
    private boolean exitButtonSoundPlayed = false;

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Check if mouse moved over play button area
        if (GamePlay.State == GamePlay.STATE.MENU && playButton.contains(mx, my)) {
            if (!playButtonSoundPlayed) {
//                System.out.println("Mouse moved over play button area");
                mouse.setFile(3);
                mouse.play();
                playButtonSoundPlayed = true; // Set flag to true after playing the sound
            }
        } else {
            // Reset flag if mouse exits play button area
            playButtonSoundPlayed = false;
        }

        // Check if mouse moved over exit button area
        if (GamePlay.State == GamePlay.STATE.MENU && exitButton.contains(mx, my)) {
            if (!exitButtonSoundPlayed) {
                System.out.println("Mouse moved over exit button area");
                mouse.setFile(3);
                mouse.play();
                exitButtonSoundPlayed = true; // Set flag to true after playing the sound
            }
        } else {
            // Reset flag if mouse exits exit button area
            exitButtonSoundPlayed = false;
        }
    }



}
