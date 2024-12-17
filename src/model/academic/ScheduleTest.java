package model.academic;

import enums.LESSON_TYPE;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ScheduleTest {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        Course course = new Course("CSCI2106", "PP1", 3, "IS");

        LocalTime localTime = LocalTime.of(9, 0);
        System.out.println(localTime);
        schedule.addCourseSession(course, LESSON_TYPE.valueOf("LECTURE"), DayOfWeek.MONDAY, localTime);
        schedule.display();
    }
}
