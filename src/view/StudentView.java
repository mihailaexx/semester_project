package view;

import controller.StudentController;
import enums.LESSON_TYPE;
import model.academic.Course;
import model.academic.Mark;
import model.academic.Schedule;
import model.people.Student;
import service.TeacherService;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentView {
    private final Scanner scanner;
    private final CourseView courseView;
    private final TeacherService teacherService;

    public StudentView(Scanner scanner, CourseView courseView, TeacherService teacherService) {
        this.scanner = scanner;
        this.courseView = courseView;
        this.teacherService = teacherService;
    }

    public int displayStudentMenu() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. View Transcript"); // finish
        System.out.println("2. View Courses"); // all courses
        System.out.println("3. Register for Course"); // send request to or manager
        System.out.println("4. View Marks");
        System.out.println("5. Rate Teacher"); // implement it
        System.out.println("6. View Schedule");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void displayTranscript(String transcript) {
        System.out.println(transcript);
    }

    public void displayCourses(List<Course> courses) {
        courseView.displayCourses(courses);
    }

    public String promptForCourseRegistration() {
        scanner.nextLine();
        System.out.print("\nEnter the code of the course to register for: ");
        return scanner.nextLine();
    }

    public void displayRegistrationSuccess(String courseCode) {
        System.out.println("Successfully sent request for course: " + courseCode);
    }

    public void displayRegistrationFailure(String reason) {
        System.out.println("Course registration failed: " + reason);
    }

    public void displayMarks(Student student) {
        Map<Course, Mark> marks = student.getMarks();
        if (marks.isEmpty()) {
            System.out.println("No marks available for this student.");
            return;
        }

        System.out.println("Marks for " + student.getName() + " " + student.getSurname() + ":");
        for (Map.Entry<Course, Mark> entry : marks.entrySet()) {
            Course course = entry.getKey();
            Mark mark = entry.getValue();
            System.out.println("  Course: " + course.getName() + ", Mark: " + mark.getTotalMark() + " (" + mark.getStringMark() + ")");
        }
    }

    public void displaySchedule(Schedule schedule) {
        System.out.println("\nStudent Schedule:");
        if (schedule == null) {
            System.out.println("No schedule to display.");
            return;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getValue() > Schedule.NUMBER_OF_WORKING_DAYS) continue;
            System.out.println(day + ":");
            Map<LocalTime, Schedule.ScheduledClass> daySchedule = schedule.getScheduleForDay(day);

            for (int hour = Schedule.START_HOUR; hour < Schedule.END_HOUR; hour++) {
                LocalTime time = LocalTime.of(hour, 0);
                Schedule.ScheduledClass session = daySchedule.get(time);
                String formattedTime = time.format(timeFormatter) + " - " + time.plusHours(1).format(timeFormatter);

                if (session != null) {
                    Course course = session.getCourse();
                    LESSON_TYPE lessonType = session.getLessonType();
                    System.out.println(formattedTime + ": " + course.getCode() + " - " + course.getName() + " (" + lessonType + ")");
                } else {
                    System.out.println(formattedTime + ": Free");
                }
            }
            System.out.println();
        }
    }

    public void displayRateTeacherForm(Student student, StudentController studentController) {
        scanner.nextLine();
        System.out.print("\nEnter the ID of the teacher to rate: ");
        String teacherId = scanner.nextLine();

        System.out.print("Enter your rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        try {
            studentController.addRating(student, teacherId, rating, teacherService);
            System.out.println("Rating added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding rating: " + e.getMessage());
        }
    }
    public void displayErrorMessage(String s) {
        System.err.println("Error: " + s);
    }
}