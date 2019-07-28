package main.java.ua.verkholiak.andrii;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

class MusicPlayer implements Runnable {

    private String musicFile;

    MusicPlayer(String musicFile) {
        this.musicFile = musicFile;
    }

    @Override
    public void run() {
        try {
            URL uriToMusicFile = getClass().getClassLoader().getResource(musicFile).toURI().toURL();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(uriToMusicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
