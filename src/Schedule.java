import enums.DISCIPLINETYPE;

public class Schedule {
    protected final String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private final Pair<Discipline, DISCIPLINETYPE>[][] schedule = new Pair[14][7];

    public Schedule() {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 7; j++) {
                schedule[i][j] = new Pair<>(new Discipline(), DISCIPLINETYPE.LECTURE); // Discipline, DisciplineType
            }
        }
    }

    public void addDiscipline(Discipline discipline, DISCIPLINETYPE disciplineType, int day, int time) {
        schedule[time-8][day] = new Pair<>(discipline, disciplineType);
    }

    public void display() {
        System.out.println("========================");
        for (int i = 0; i < 7; i++) {
            System.out.println(days[i]);
            System.out.println();
            for (int j = 0; j < 14; j++) {
                Discipline d = schedule[j][i].getKey();
                System.out.println(String.format("%02d:00-%02d:00", 8+i, 8+i+1) + d.getCode() + " " + d.getName() + ", " + schedule[j][i].getValue());
            }
            System.out.println();
        System.out.println("========================");
        }
    }
}
