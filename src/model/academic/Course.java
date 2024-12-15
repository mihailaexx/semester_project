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
    private int year;
    private int semester;

    private Schedule schedule;
    private List<Teacher> instructors;
    private Map<Student, Mark> studentMarks;

    public Course(String code, String name, int credits, int year, int semester) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.year = year;
        this.semester = semester;
        this.schedule = new Schedule();
        this.instructors = new ArrayList<>();
        this.studentMarks = new HashMap<>();
    }

    public void addInstructor(Teacher teacher) {instructors.add(teacher);}
    public void addSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public void updateStudentMark(Student student, double mark, int attestationIndex) {
        Mark m = studentMarks.get(student);
        if (m == null) {
            m = new Mark(0, 0, 0);
        }
        m.updateMark(mark, attestationIndex);
        studentMarks.put(student, m);
    }
    public Schedule getSchedule() {
        return schedule;
    }
    public Mark getMarkForStudent(Student s) { return studentMarks.get(s);}
    public String getCode() {return code;}
    public String getName() {return name;}
    public int getCredits() {return credits;}
    public int getYear() {return year;}
    public int getSemester() {return semester;}
    public List<Teacher> getInstructors() {return instructors;}

    @Override
    public String toString() {
        return "Course[" +
                "code=" + code +
                ", name=" + name +
                ", credits=" + credits +
                ", year=" + year +
                ", semester=" + semester +
                ", instructors=" + instructors +
                ", schedule=" + schedule +
                ']';
    }
}
