package model.academic;

import model.people.Student;
import model.people.Teacher;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {
    private static final long serialVersionUID = 12L;

    private String code;
    private String name;
    private int credits;
    private String major;
    private Schedule schedule;

    private List<Teacher> instructors;
    private Map<Student, Mark> studentMarks;

    public Course(String code, String name, int credits, String major) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.major = major;
        this.instructors = new ArrayList<>();
        this.studentMarks = new HashMap<>();
        this.schedule = new Schedule();
    }

    public void addInstructor(Teacher teacher) {instructors.add(teacher);}
    public void updateStudentMark(Student student, double mark, int attestationIndex) {
        Mark m = studentMarks.get(student);
        if (m == null) {
            m = new Mark(0, 0, 0);
        }
        m.updateMark(mark, attestationIndex);
        studentMarks.put(student, m);
    }
    public Mark getMarkForStudent(Student s) { return studentMarks.get(s);}
    public String getCode() {return code;}
    public String getName() {return name;}
    public int getCredits() {return credits;}
    public List<Teacher> getInstructors() {return instructors;}
    public String getMajor() { return major; }
    public void setCode(String code) { this.code = code; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setMajor(String major) { this.major = major; }
    public void setInstructors(List<Teacher> instructors) {this.instructors = instructors;}
    public void setName(String name) { this.name = name; }
    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule;}
    public void addStudentMark(Student student, Mark mark) { studentMarks.put(student, mark); }
    @Override
    public String toString() {
        return "Course[" +
                "code=" + code +
                ", name=" + name +
                ", credits=" + credits +
                ", instructors=" + instructors +
                ']';
    }
}
