package ru.aston.ian_ev.task10;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Component
@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClassicalMusic implements Music {
    private String music;

    @PostConstruct
    public void init() {
        System.out.println("init method");
        music = List.of("Beethoven - Fur Elise", "Vivaldi - Four Seasons", "Grieg - In the Hall of the Mountain King")
                .get(new Random().nextInt(3));
    }
    @Override
    public String getMusic() {
        return music;
    }
}
