package ru.aston.ian_ev.task10;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.aston.ian_ev.task10.java.Config;

public class AnnotationConfigApplicationContextTest {
    @Test
    void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        MusicPlayer musicPlayer = applicationContext.getBean(MusicPlayer.class);
        System.out.println(musicPlayer.getModel());
        musicPlayer.play(applicationContext.getBean(Music.class));
        musicPlayer.play(applicationContext.getBean(Music.class));
        musicPlayer.play(applicationContext.getBean(Music.class));
        musicPlayer.play(applicationContext.getBean(Music.class));
        musicPlayer.play(applicationContext.getBean(Music.class));
        musicPlayer.play(applicationContext.getBean(Music.class));
        musicPlayer.play(applicationContext.getBean(Music.class));
    }
}
