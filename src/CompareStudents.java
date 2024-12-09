import model.people.Student;
import org.jetbrains.annotations.NotNull;

public interface CompareStudents {
    default int compare(@NotNull Student student1, @NotNull Student student2) {
        if (student1.getGpa() > student2.getGpa()) {
            return 1;
        } else if (student1.getGpa() < student2.getGpa()) {
            return -1;
        } else {
            return 0;
        }
    }
}
