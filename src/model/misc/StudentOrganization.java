package model.misc;

import model.people.Student;

import java.util.Objects;
import java.util.Vector;

public class StudentOrganization {
    private String name;
    private Student head;
    private static Vector<Student> students;

    public StudentOrganization() {
        students = new Vector<Student>();
    }
    public StudentOrganization(String name) {
        this.name = name;
        students = new Vector<Student>();
    }

    public void addMember(Student student) {
        students.add(student);
    }
    public void removeMember(Student student) {
        students.remove(student);
    }

    public void setHead(Student head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }
    public Student getHead() {
        return head;
    }
    public Vector<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StudentOrganization that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getHead(), that.getHead());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHead());
    }

    @Override
    public String toString() {
        return "StudentOrganization[" +
                "name='" + name + '\'' +
                ", head=" + head +
                ", students list:" + students +
                ']';
    }
}
