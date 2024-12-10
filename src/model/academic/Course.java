package model.academic;

import model.people.Student;
import model.people.Teacher;

import java.util.LinkedHashMap;
import java.util.List;

public class Course {
    private int year;
    private int semester;
    private LinkedHashMap <Discipline, Mark> disciplineMarkLinkedHashMap;
    private Schedule schedule;
    private List<Teacher> instructors;
    private List<Student> enrolledStudents;


    public Course(int year, int semester) {
        this.year = year;
        this.semester = semester;
        this.disciplineMarkLinkedHashMap = new LinkedHashMap<Discipline, Mark>();
        this.schedule = new Schedule();
    }
    public void addDiscipline(Discipline discipline) {
        disciplineMarkLinkedHashMap.put(discipline, null);
    }
    public void addInstructor(Teacher teacher) {instructors.add(teacher);}
    public void addSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public void updateDisciplineMark(Discipline discipline, double mark, int i) {
        Mark m = disciplineMarkLinkedHashMap.get(discipline);
        if (m != null) {
            m.updateMark(mark, i);
        } else {
            // Create mark if null
            Mark newMark = new Mark(0,0,0);
            newMark.updateMark(mark, i);
            disciplineMarkLinkedHashMap.put(discipline, newMark);
        }
    }
    public LinkedHashMap<Discipline, Mark> getDisciplineMarkLinkedHashMap() {
        return disciplineMarkLinkedHashMap;
    }
    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Course[" +
                "year=" + year +
                ", semester=" + semester +
                ", marks=" + disciplineMarkLinkedHashMap +
                ", schedule=" + schedule +
                ']';
    }
}
