package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import config.Config;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BeatMap {
    private ArrayList<ArrayList<Integer>> notes;
    private Media media;
    private MediaPlayer mediaPlayer;

    public boolean loadBeatMap(String mapPath, String musicPath) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        notes = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < Config.K_TILE; i++) {
            notes.add(new ArrayList<Integer>());
        }
        media = new Media(new File(musicPath).toURI().toString());
        if (media.getError() != null) {
            return false;
        }
        mediaPlayer = new MediaPlayer(media);

        Scanner notesReader;
        try {
            notesReader = new Scanner(new File(mapPath));
            String currentSection = "";

            while (notesReader.hasNextLine()) {
                String line = notesReader.nextLine();
                if (line.startsWith("[")) {
                    currentSection = line;
                } else {
                    switch (currentSection) {
                        case "[HitObjects]":
                            String[] lineData = line.split(",");

                            if (Config.KEY_MAPPING.containsKey(lineData[0])) {
                                int time = Integer.parseInt(lineData[2]);
                                notes.get(Config.KEY_MAPPING.get(lineData[0]))
                                        .add(time);
                            }
                            break;
                        default:
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        notesReader.close();
        return true;
    }

    public void playMusic() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.play();
        }
    }

    public int getCurrentMusicTimestamp() {
        if (this.mediaPlayer != null) {
            return (int) this.mediaPlayer.getCurrentTime().toMillis();
        }
        return -1;
    }

    public int getBeatMapNote(int kTile, int pos) {
        if (kTile < this.notes.size() && pos < this.notes.get(kTile).size()) {
            return this.notes.get(kTile).get(pos);
        }
        return -1;
    }
}
