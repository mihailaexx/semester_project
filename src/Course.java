import java.util.LinkedHashMap;

public class Course {
    private int year;
    private int semester;
    private LinkedHashMap < Discipline, Mark > disciplineMarkLinkedHashMap;
    private Schedule schedule;

    public Course(int year, int semester) {
        this.year = year;
        this.semester = semester;
        this.disciplineMarkLinkedHashMap = new LinkedHashMap<Discipline, Mark>();
        this.schedule = new Schedule();

    }
    public void addDiscipline(Discipline discipline) {
        disciplineMarkLinkedHashMap.put(discipline, null);
    }
    public void addSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public void updateDisciplineMark(Discipline discipline, double mark, int i) {
        disciplineMarkLinkedHashMap.get(discipline).updateMark(mark, i);
    }
    public LinkedHashMap<Discipline,Mark> getDisciplineMarkLinkedHashMap() {
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
