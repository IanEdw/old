package ru.aston.ian_ev.task9.models.projects;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aston.ian_ev.task9.models.people.Student;

import java.util.List;

@Entity
@Table(name = "intensive")
@Getter
@Setter
@NoArgsConstructor
public class Intensive extends Project {
    @OneToMany(mappedBy = "intensive")
    private List<Student> students;
}
