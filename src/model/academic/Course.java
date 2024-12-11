package model.academic;

import model.people.Student;
import model.people.Teacher;

import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.HashMap;

public class Course {
    private String code;
    private String name;
    private int credits;
    private int year;
    private int semester;

    private Schedule schedule;
    private Vector<Teacher> instructors;
    private Map<Student, Mark> studentMarks;

    public Course(String code, String name, int credits, int year, int semester) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.year = year;
        this.semester = semester;
        this.schedule = new Schedule();
        this.instructors = new Vector<>();
        this.studentMarks = new HashMap<>();
    }

    public void addInstructor(Teacher teacher) {
        instructors.add(teacher);
    }
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
    public Map<Student, Mark> getStudentMarks() {
        return new HashMap<>(studentMarks);
    }
    public Mark getMarkForStudent(Student s) {
        return studentMarks.get(s);
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getCredits() {
        return credits;
    }
    public int getYear() {
        return year;
    }
    public int getSemester() {
        return semester;
    }
    public Vector<Teacher> getInstructors() {
        return new Vector<>(instructors);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Course course)) return false;
        return getCredits() == course.getCredits() && getYear() == course.getYear() && getSemester() == course.getSemester() && Objects.equals(getCode(), course.getCode()) && Objects.equals(getName(), course.getName()) && Objects.equals(getSchedule(), course.getSchedule()) && Objects.equals(getInstructors(), course.getInstructors()) && Objects.equals(getStudentMarks(), course.getStudentMarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName(), getCredits(), getYear(), getSemester(), getSchedule(), getInstructors(), getStudentMarks());
    }

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
