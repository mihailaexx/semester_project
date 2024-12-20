package view;

import controller.TeacherController;
import data.DataStore;
import model.academic.Course;
import model.academic.Schedule;
import model.people.Student;
import model.people.Teacher;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TeacherView {
    private final Scanner scanner;
    private final CourseView courseView;

    public TeacherView(Scanner scanner, CourseView courseView) {
        this.scanner = scanner;
        this.courseView = courseView;
    }

    public int displayTeacherMenu() {
        System.out.println("\nTeacher Menu:");
        System.out.println("1. View Courses");
        System.out.println("2. View Students in Course");
        System.out.println("3. Put Mark for Student"); // revisit it
        System.out.println("4. Send Complaint"); // implement it
        System.out.println("5. View Schedule");
        System.out.println("6. Send Message");
        System.out.println("7. View Messages");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void displayCourses(List<Course> courses) {
        System.out.println("\nCourses:");
        courseView.displayCourses(courses);
    }

    public String promptForCourseSelection() {
        System.out.print("\nEnter the code of the course: ");
        return scanner.nextLine();
    }

    public void displayStudents(Teacher teacher, String courseCode, DataStore dataStore) {
        System.out.println("\nStudents in course " + courseCode + ":");
        Course selectedCourse = teacher.getCourses().stream()
                .filter(c -> c.getCode().equals(courseCode))
                .findFirst()
                .orElse(null);

        if (selectedCourse != null) {
            List<Student> students = dataStore.getAllStudents();
            students.stream()
                    .filter(s -> s.getEnrolledCourses().contains(selectedCourse))
                    .forEach(student -> System.out.println(student.getName() + " " + student.getSurname() + " (" + student.getStudentID() + ")"));
        } else {
            System.out.println("Course not found or teacher is not assigned to this course.");
        }
    }

    public String promptForStudentSelection() {
        System.out.print("\nEnter the ID of the student: ");
        return scanner.nextLine();
    }

    public double promptForMark(String markType) {
        System.out.print("Enter " + markType + " mark: ");
        double mark = scanner.nextDouble();
        scanner.nextLine();
        return mark;
    }

    public void displayMarkInputSuccess() {
        System.out.println("Mark input successful.");
    }

    public void displayMarkInputFailure(String reason) {
        System.out.println("Mark input failed: " + reason);
    }

    public String[] promptForComplaint() {
        System.out.print("Enter student ID for the complaint: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter complaint message: ");
        String message = scanner.nextLine();
        System.out.print("Enter urgency (LOW, MEDIUM, HIGH): ");
        String urgency = scanner.nextLine().toUpperCase();
        return new String[]{studentId, message, urgency};
    }

    public void displayComplaintSent() {
        System.out.println("Complaint sent successfully.");
    }

    public void displayComplaintFailure(String reason) {
        System.out.println("Complaint sending failed: " + reason);
    }

    public void displayMarkInputForm(Teacher teacher, DataStore dataStore, TeacherController teacherController) {
        try {
            System.out.print("Enter the code of the course: ");
            String courseCode = scanner.nextLine();

            Course selectedCourse = teacher.getCourses().stream()
                    .filter(c -> c.getCode().equals(courseCode))
                    .findFirst()
                    .orElse(null);

            if (selectedCourse == null) {
                System.out.println("Course not found or teacher is not assigned to this course.");
                return;
            }

            System.out.print("Enter the ID of the student: ");
            String studentId = scanner.nextLine();

            Student student = dataStore.getStudentById(studentId);
            if (student == null || !student.getEnrolledCourses().contains(selectedCourse)) {
                System.out.println("Student not found or not enrolled in this course.");
                return;
            }

            double att1 = promptForMarkWithValidation("Attestation 1", 0, 30);
            double att2 = promptForMarkWithValidation("Attestation 2", 0, 30);
            double finalExam = promptForMarkWithValidation("Final Exam", 0, 40);

            teacherController.addMark(teacher, courseCode, studentId, att1, att2, finalExam);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter numeric values for marks.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private double promptForMarkWithValidation(String markType, int min, int max) {
        while (true) {
            System.out.print("Enter " + markType + " mark (" + min + "-" + max + "): ");
            if (scanner.hasNextDouble()) {
                double mark = scanner.nextDouble();
                if (mark >= min && mark <= max) {
                    return mark;
                } else {
                    System.out.println("Invalid mark. Please enter a value between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
        }
    }
    public void displayComplaintForm(Teacher teacher, DataStore dataStore, TeacherController teacherController) {
        String[] complaintDetails = promptForComplaint();
        String studentId = complaintDetails[0];
        String message = complaintDetails[1];
        String urgency = complaintDetails[2];

        Student student = dataStore.getStudentById(studentId);
        if (student == null) {
            displayComplaintFailure("Student with ID " + studentId + " not found.");
            return;
        }

        try {
            teacherController.sendComplaint(teacher, studentId, message, urgency);
            displayComplaintSent();
        } catch (Exception e) {
            displayComplaintFailure(e.getMessage());
        }
    }

    public void displaySchedule(Schedule schedule) {
        System.out.println("\nTeacher Schedule:");
        if (schedule == null) {
            System.out.println("No schedule to display.");
            return;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day + ":");
            Map<LocalTime, Schedule.ScheduledClass> daySchedule = schedule.getScheduleForDay(day);

            for (int hour = Schedule.START_HOUR; hour < Schedule.END_HOUR; hour++) {
                LocalTime time = LocalTime.of(hour, 0);
                Schedule.ScheduledClass session = daySchedule.get(time);
                String formattedTime = time.format(timeFormatter) + " - " + time.plusHours(1).format(timeFormatter);

                if (session != null) {
                    Course course = session.getCourse();
                    System.out.println(formattedTime + ": " + course.getCode() + " - " + course.getName() + " (" + session.getLessonType() + ")");
                } else {
                    System.out.println(formattedTime + ": Free");
                }
            }
            System.out.println();
        }
    }

    public void displayErrorMessage(String s) {
        System.out.println("Error: " + s);
    }
}