package ru.aston.ian_ev.task9;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.aston.ian_ev.task9.models.Position;
import ru.aston.ian_ev.task9.models.people.Employee;
import ru.aston.ian_ev.task9.models.people.Student;
import ru.aston.ian_ev.task9.models.projects.CommercialProject;
import ru.aston.ian_ev.task9.models.projects.Intensive;
import ru.aston.ian_ev.task9.models.projects.Project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Utils.executeSqlScripts("sql/task9/schema.sql", "application_task9");
        try (SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Intensive.class)
                .addAnnotatedClass(CommercialProject.class)
                .buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                CommercialProject commercialProject = new CommercialProject();
                commercialProject.setName("MainService");
                commercialProject.setCustomer("Sber");

                Intensive intensive = new Intensive();
                intensive.setName("Intesive34");

                Employee employee = new Employee();
                employee.setSalary(9999999999L);
                employee.setName("Konstantin");
                employee.setPosition(Position.MIDDLE);


                Student student = new Student();
                student.setName("Edward");


                session.getTransaction().begin();

                session.persist(commercialProject);
                session.persist(intensive);
                session.persist(employee);
                session.persist(student);

                employee.setProjects(new ArrayList<>(List.of(commercialProject, intensive)));
                student.setIntensive(intensive);
                student.setMentor(employee);
                commercialProject.setEmployees(new ArrayList<>(List.of(employee)));
                intensive.setStudents(new ArrayList<>(List.of(student)));
                intensive.setEmployees(new ArrayList<>(List.of(employee)));
                employee.setStudents(new ArrayList<>(List.of(student)));

                session.getTransaction().commit();
            }

            try (Session session = sessionFactory.openSession()) {
                //Можно тут стопнуть дебагером и посмотреть что внутри бд
                // Использовал H2 поэтому должно работать если просто склонировать репу
                // Связи и полиморфные запросы работают
                Employee employee = session.get(Employee.class, 1);

                System.out.println(employee.getId());
            }
        }
    }
}
