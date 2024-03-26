import javax.swing.*;
public class Main{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
             JFrame f =new JFrame("DodgeIt");
             GamePlay gp = new GamePlay(f);
             f.add(gp);
             f.setSize(694,600);
             f.setVisible(true);
             f.setResizable(false);
             f.setLocationRelativeTo(null);
             f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
