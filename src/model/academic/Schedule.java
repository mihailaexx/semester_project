package model.academic;

import enums.LESSON_TYPE;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 18L;

    public static final int START_HOUR = 8;
    public static final int END_HOUR = 21;
    public static final int NUMBER_OF_WORKING_DAYS = 6;

    private Map<DayOfWeek, Map<LocalTime, ScheduledClass>> schedule;

    public Schedule() {
        schedule = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getValue() > NUMBER_OF_WORKING_DAYS) break;
            Map<LocalTime, ScheduledClass> daySchedule = new HashMap<>();
            for (int hour = START_HOUR; hour < END_HOUR; hour++) {
                daySchedule.put(LocalTime.of(hour, 0), null);
            }
            schedule.put(day, daySchedule);
        }
    }

    /**
     * Add a course session at a given day and start time.
     *
     * @param course     The course to be scheduled.
     * @param lessonType The type of lesson.
     * @param day        The day of the week.
     * @param time       The start time of the lesson.
     */
    public void addCourseSession(Course course, LESSON_TYPE lessonType, DayOfWeek day, LocalTime time) {
        if (day.getValue() > NUMBER_OF_WORKING_DAYS) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        if (time.getHour() < START_HOUR || time.getHour() >= END_HOUR) {
            throw new IllegalArgumentException("Invalid time: " + time + ". Must be between " + START_HOUR + ":00 and " + END_HOUR + ":00");
        }

        schedule.get(day).put(time, new ScheduledClass(course, lessonType));
    }

    public void display() {
        System.out.println("======================== SCHEDULE ========================");
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getValue() > NUMBER_OF_WORKING_DAYS) break;
            System.out.println(day + ":");

            Map<LocalTime, ScheduledClass> daySchedule = schedule.get(day);
            for (int hour = START_HOUR; hour < END_HOUR; hour++) {
                LocalTime time = LocalTime.of(hour, 0);
                ScheduledClass session = daySchedule.get(time);

                System.out.print(time + "-" + time.plusHours(1) + " ");
                if (session != null) {
                    System.out.println(session.getCourse().getCode() + " " + session.getCourse().getName() + ", " + session.getLessonType());
                } else {
                    System.out.println(" - ");
                }
            }
            System.out.println();
        }
        System.out.println("==========================================================");
    }

    public Map<LocalTime, ScheduledClass> getScheduleForDay(DayOfWeek day) {
        return schedule.getOrDefault(day, new HashMap<>());
    }

    // Inner class to represent a scheduled class with course and lesson type
    public static class ScheduledClass implements Serializable {
        private final Course course;
        private final LESSON_TYPE lessonType;

        public ScheduledClass(Course course, LESSON_TYPE lessonType) {
            this.course = course;
            this.lessonType = lessonType;
        }

        public Course getCourse() {
            return course;
        }

        public LESSON_TYPE getLessonType() {
            return lessonType;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule1 = (Schedule) o;
        return Objects.equals(schedule, schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schedule=" + schedule +
                '}';
    }
}
