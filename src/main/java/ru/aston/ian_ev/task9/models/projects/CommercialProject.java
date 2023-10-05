package ru.aston.ian_ev.task9.models.projects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commercial_project")
@Getter
@Setter
public class CommercialProject extends Project {
    private String customer;
}
