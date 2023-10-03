package ru.aston.ian_ev.task9.models.people;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aston.ian_ev.task9.models.Position;
import ru.aston.ian_ev.task9.models.projects.Project;

import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {
    private long salary;
    @Enumerated(EnumType.STRING)
    private Position position;
    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private List<Project> projects;
    @OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
    private List<Student> students;
}
