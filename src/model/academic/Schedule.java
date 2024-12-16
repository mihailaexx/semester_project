package model.academic;

import enums.LESSON_TYPE;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 18L;

    protected final String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private final Pair<Course, LESSON_TYPE>[][] schedule = new Pair[14][7];

    public Schedule() {
        for (int hour = 0; hour < 14; hour++) {
            for (int day = 0; day < 7; day++) {
                schedule[hour][day] = null;
            }
        }
    }

    /**
     * Add a course session at a diven day and start hour.
     * @param day: 0 = Mon, 1 = Tue, ..., 6 = Sun
     * @param time: the hour in 24-hour format, must be between 8 and 21
     */
    public void addCourseSession(Course course, LESSON_TYPE lessonType, int day, int time) {
        if (day < 0 || day > 6) {
            throw new IllegalArgumentException("Invalid day index: " + day);
        }
        int hourIndex = time - 8; // 0 corresponds to 08:00
        if (hourIndex < 0 || hourIndex >= 14) {
            throw new IllegalArgumentException("Invalid time:  " + time + ". Must be between 8 and 21");
        }

        schedule[hourIndex][day] = new Pair<>(course, lessonType);
    }

    public void display() {
        System.out.println("======================== SCHEDULE ========================");
        for (int day = 0; day < 7; day++) {
            System.out.println(days[day] + ":");

            for (int hour = 0; hour < 14; hour++) {
                Pair<Course, LESSON_TYPE> slot = schedule[hour][day];
                int startHour = 8 + hour;
                int endHour = startHour + 1;

                if (slot != null) {
                    Course c =  slot.getKey();
                    LESSON_TYPE lessonType = slot.getValue();
                    System.out.println(String.format("%02d:00-%02d:00", startHour, endHour) + " " +
                            c.getCode() + " " + c.getName() + ", " + lessonType);
                } else {
                    System.out.println(String.format("%02d:00-%02d:00", startHour, endHour) + " ");
                }
            }
            System.out.println();
        }
        System.out.println("==========================================================");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Schedule schedule1)) return false;
        return Objects.deepEquals(days, schedule1.days) && Objects.deepEquals(schedule, schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(days), Arrays.deepHashCode(schedule));
    }

    @Override
    public String toString() {
        display();
        return "";
    }
}
