package ru.aston.ian_ev.task10;

import javax.annotation.PreDestroy;

public class MusicPlayer {
    private String model;

    public MusicPlayer(String model) {
        this.model = model;
    }

    public void play(Music music) {
        System.out.println(music.getMusic());
    }

    public String getModel() {
        return model;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy method");
    }
}
