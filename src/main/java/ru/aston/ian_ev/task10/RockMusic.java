package ru.aston.ian_ev.task10;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RockMusic implements Music {
    private String music;

    @PostConstruct
    public void init() {
        System.out.println("init method");
        music = List.of("Metallica - Master of Puppets", "Rammstein - Du Hast", "Disturbed - deceiver")
                .get(new Random().nextInt(3));
    }
    @Override
    public String getMusic() {
        return music;
    }
}
