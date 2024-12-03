import java.util.Vector;

public class StudentOrganization {
    private String name;
    private Student head;
    private Vector<Student> students;

    public StudentOrganization() {
        this.students = new Vector<Student>();
    }
    public StudentOrganization(String name) {
        this.name = name;
        this.students = new Vector<Student>();
    }

    public String getName() {
        return name;
    }

    public void addMember(Student student) {
        students.add(student);
    }

    public void removeMember(Student student) {
        students.remove(student);
    }
}
