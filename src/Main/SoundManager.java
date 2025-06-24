package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundManager {
    Clip clip;
    URL[] soundURL = new URL[30];
    public SoundManager() {
        soundURL[0] = getClass().getResource("/sound/musica.wav");
        soundURL[1] = getClass().getResource("/sound/pickup.wav");
        soundURL[2] = getClass().getResource("/sound/moneda.wav");
        soundURL[3] = getClass().getResource("/sound/caminarPiedra.wav");
        soundURL[4] = getClass().getResource("/sound/caminarPasto.wav");
        soundURL[5] = getClass().getResource("/sound/machete.wav");
        soundURL[6] = getClass().getResource("/sound/speed.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
