package ru.aston.ian_ev.task9.models.people;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.aston.ian_ev.task9.models.projects.Intensive;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", referencedColumnName = "id")
    private Employee mentor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intensive_id", referencedColumnName = "id")
    private Intensive intensive;
}
