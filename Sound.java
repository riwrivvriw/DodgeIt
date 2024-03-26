import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    FloatControl fc ;

    URL[] soundURL = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/res/music.wav");
        soundURL[1] = getClass().getResource("/res/die.wav");
        soundURL[2] = getClass().getResource("/res/enter.wav");
        soundURL[3] = getClass().getResource("/res/mouseEnterReVol.wav");
        soundURL[4] = getClass().getResource("/res/win.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream main = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(main);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        clip.setFramePosition(0);
        fc.setValue(-20.0f);
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
