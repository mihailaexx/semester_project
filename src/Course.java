import java.util.LinkedHashMap;

public class Course {
    private int year;
    private int semester;
    private LinkedHashMap < Discipline, Mark > disciplineMarkLinkedHashMap;
    private Schedule schedule;

    public Course(int year, int semester) {
        this.year = year;
        this.semester = semester;
        disciplineMarkLinkedHashMap = new LinkedHashMap<>();
        schedule = new Schedule();
    }

    public void addSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    
}
