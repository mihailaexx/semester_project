import java.util.Arrays;
import java.util.List;

public class Schedule {
    private final List<Integer>[][] matrix = new List[14][7];

    public Schedule() {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 7; j++) {
                matrix[i][j] = Arrays.asList(0, 0, 0);
            }
        }
    }
}

/*
Discipline, type

 */