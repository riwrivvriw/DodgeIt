import java.awt.*;
public class MainMenu {
    
    public Rectangle playButton = new Rectangle(300, 300, 120, 70);
    public Rectangle exitButton = new Rectangle(185, 400, 350, 70);

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;



        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("Dodge it if you can!", 130, 200);
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Let's try", playButton.x, playButton.y + 45);
        g.drawString("I wanna Exit i'm noob", playButton.x - 90, playButton.y + 145);

        g2.draw(playButton);
        g2.draw(exitButton);
    }
}
