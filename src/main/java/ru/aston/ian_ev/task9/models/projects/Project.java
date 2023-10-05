package ru.aston.ian_ev.task9.models.projects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.aston.ian_ev.task9.models.people.Employee;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proj_seq")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;
}
