package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Listener {

    public static void main(String[] args) {

        while (true) {
            try {
                Document doc = Jsoup.connect("http://10.229.17.62:8081/abc").get();
                String audio = doc.body().text();

                if (audio != null) {
                    if (!audio.equals("none")) {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("./" + audio)));
                        long microseconds = clip.getMicrosecondLength() / 1000;
                        System.out.println("sleeping " + microseconds);
                        clip.start();
                        Thread.sleep(microseconds);
                    }
                } else {
                    System.out.println("invalid audio: " + audio);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
