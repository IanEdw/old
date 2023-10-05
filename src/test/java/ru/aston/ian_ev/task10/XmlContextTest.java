package ru.aston.ian_ev.task10;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlContextTest {
    @Test
    void testContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        MusicPlayer musicPlayer = applicationContext.getBean(MusicPlayer.class);
        System.out.println(musicPlayer.getModel());
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        musicPlayer.play(applicationContext.getBean(RockMusic.class));
        applicationContext.close();
    }
}
