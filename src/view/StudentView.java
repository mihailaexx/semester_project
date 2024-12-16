package view;

import enums.LESSON_TYPE;
import model.academic.Course;
import model.academic.Mark;
import model.academic.Schedule;
import model.people.Student;
import model.people.Teacher;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentView {
    private final Scanner scanner;
    private final CourseView courseView;
    public StudentView(Scanner scanner, CourseView courseView) {
        this.scanner = scanner;
        this.courseView = courseView;
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

    public void displayTranscript(Student student) {
        System.out.println("Transcript for: " + student.getName() + " " + student.getSurname() + " (ID: " + student.getStudentID() + ")");
        for (Course course : student.getEnrolledCourses()) {
            Mark mark = student.getMarks().get(course);
            if (mark != null) {
                double finalGrade = student.calculateFinalGrade(mark);
                double gradePoint = student.gradeToGpa(finalGrade);

                System.out.printf("Course: %s (Credits: %d) Final Grade: %.2f (GPA: %.2f)%n",
                        course.getName(), course.getCredits(), finalGrade, gradePoint);
            } else {
                System.out.println("Course: " + course.getName() + " (No marks yet)");
            }
        }
        student.calculateGpa();
        System.out.printf("Overall GPA: %.2f%n", student.getGpa());
    }

    public void displayCourses(List<Course> courses) {
        System.out.println("\nCourses:");
        courseView.displayCourses(courses);
    }

    public String promptForCourseRegistration() {
        scanner.nextLine(); // Consume leftover newline
        System.out.print("\nEnter the code of the course to register for: ");
        return scanner.nextLine();
    }

    public void displayRegistrationSuccess(String courseCode) {
        System.out.println("Successfully registered for course: " + courseCode);
    }

    public void displayRegistrationFailure(String reason) {
        System.out.println("Course registration failed: " + reason);
    }

    public void displayMarks(Student student) {
        for (Course course : student.getEnrolledCourses()) {
            Mark mark = student.getMarks().get(course);
            if (mark != null) {
                System.out.println(course.getName() + ": " + mark);
            } else {
                System.out.println(course.getName() + ": No marks yet.");
            }
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
    public void displayRateTeacherForm(Student student) {
        scanner.nextLine();
        System.out.print("\nEnter the ID of the teacher to rate: ");
        String teacherId = scanner.nextLine();

        System.out.print("Enter your rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        // In a real application, you would fetch the Teacher object from the data store
        // and then call a method on the Teacher object to add the rating.
        // For now, let's assume a method 'addRating' in a hypothetical Teacher class.

        // Placeholder: Assuming a method to find a teacher by ID in DataStore
        // Teacher teacher = dataStore.getTeacherById(teacherId);

        // Placeholder: Assuming a method 'addRating' in Teacher class
        // if (teacher != null) {
        //     teacher.addRating(rating);
        //     System.out.println("Rating added successfully.");
        // } else {
        //     System.out.println("Teacher not found.");
        // }
    }
    public void displayErrorMessage(String s) {
        System.err.println("Error: " + s);
    }
}