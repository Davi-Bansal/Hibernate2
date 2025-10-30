package com.example;

import org.hibernate.*;
import java.util.List;

public class StudentMain {

    public static void main(String[] args) {

        // CREATE
        insertStudent(new Student(1, "Juhi", "Java", 90));

        // READ
        getStudent(1);
        getAllStudents();

        // UPDATE
        updateStudent(1, "Spring Boot", 95);

        // DELETE
        deleteStudent(1);
    }

    // CREATE
    static void insertStudent(Student s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(s);
        tx.commit();
        session.close();
        System.out.println("Student Inserted");
    }

    // READ by ID
    static void getStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student s = session.get(Student.class, id);
        System.out.println("Student: " + s.getName() + ", " + s.getCourse());
        session.close();
    }

    // READ all
    static void getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> list = session.createQuery("from Student").list();
        list.forEach(s -> System.out.println(s.getId()+" - "+s.getName()));
        session.close();
    }

    // UPDATE
    static void updateStudent(int id, String course, int marks) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);
        s.setCourse(course);
        s.setMarks(marks);

        session.update(s);
        tx.commit();
        session.close();
        System.out.println("Student Updated");
    }

    // DELETE
    static void deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);
        session.delete(s);

        tx.commit();
        session.close();
        System.out.println("Student Deleted");
    }
}
