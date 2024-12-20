package service;

import data.DataStore;
import enums.SEX;
import enums.STUDENTTYPE;
import enums.TEACHERDEGREE;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.manager.Request;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import view.UserView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrManagerService {
    private final DataStore dataStore;
    private final UserView userView;


    public OrManagerService(DataStore dataStore, UserView userView) {
        this.dataStore = dataStore;
        this.userView = userView;
    }

    public void addRegistrationRequest(Request request) {
        dataStore.addRegistrationRequest(request);
    }

    public void approveRegistrationRequest(int requestId) {
        Request request = dataStore.getRequestById(requestId);
        if (request != null) {
            // Check if the request is still pending
            if (request.getStatus().equals("PENDING")) {
                try {
                    // Enroll the student in the course
                    request.getStudent().registerForCourse(request.getCourse());
                    request.setStatus("APPROVED");

                    // Update student and course in the data store
                    dataStore.updateStudent(request.getStudent());
                    dataStore.updateCourse(request.getCourse());

                    // Optionally, notify the student about the approval
                    System.out.println("Registration request " + requestId + " approved.");
                } catch (CourseRegistrationException e) {
                    System.err.println("Error approving registration request: " + e.getMessage());
                    // Consider updating the request status to indicate failure
                    request.setStatus("FAILED");
                } finally {
                    dataStore.saveRequest(request);
                }
            } else {
                System.err.println("Request " + requestId + " is not pending.");
            }
        } else {
            System.err.println("Request not found: " + requestId);
        }
    }

    public void rejectRegistrationRequest(int requestId) {
        Request request = dataStore.getRequestById(requestId);
        if (request != null) {
            request.setStatus("REJECTED");
            dataStore.saveRequest(request);
            System.out.println("Registration request " + requestId + " rejected.");
        } else {
            System.err.println("Request not found: " + requestId);
        }
    }
    public List<Request> getPendingRequests() {
        return dataStore.getAllRequests().stream()
                .filter(r -> r.getStatus().equals("PENDING"))
                .collect(Collectors.toList());
    }

    public void createStatisticalReport() {
        System.out.println("Statistical Report:");

        List<Student> students = dataStore.getAllStudents();
        List<Teacher> teachers = dataStore.getAllTeachers();
        List<Employee> employees = dataStore.getAllEmployees();

        System.out.println("\nStudent Statistics:");
        System.out.println("Total Students: " + students.size());
        if (!students.isEmpty()) {
            double averageGpa = students.stream()
                    .mapToDouble(Student::getGpa)
                    .average()
                    .orElse(0.0);
            System.out.printf("Average GPA: %.2f\n", averageGpa);
        }

        System.out.println("\nTeacher Statistics:");
        System.out.println("Total Teachers: " + teachers.size());
        if (!teachers.isEmpty()) {
            double averageRating = teachers.stream()
                    .mapToDouble(Teacher::getAverageRating)
                    .average()
                    .orElse(0.0);
            System.out.printf("Average Rating: %.2f\n", averageRating);
        }

        System.out.println("\nEmployee Statistics:");
        System.out.println("Total Employees: " + employees.size());

        // Additional statistics can be added here
    }

    public void viewStudentsByGPA() {
        List<Student> students = dataStore.getAllStudents();
        students.sort(Comparator.comparing(Student::getGpa).reversed());

        System.out.println("Students sorted by GPA (highest to lowest):");
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getSurname() + " - GPA: " + student.getGpa());
        }
    }

    public void viewTeachersAlphabetically() {
        List<Teacher> teachers = dataStore.getAllTeachers();
        teachers.sort(Comparator.comparing(Teacher::getSurname).thenComparing(Teacher::getName));

        System.out.println("Teachers sorted alphabetically (by surname, then name):");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getSurname() + ", " + teacher.getName());
        }
    }

    public void viewCourses() {
        dataStore.getAllCourses();
    }

    public void viewTeachers() {
        dataStore.getAllTeachers();
    }

    public void viewStudents() {
        dataStore.getAllStudents();
    }

    public void viewEmployees() {
        dataStore.getAllEmployees();
    }

    public void viewUsers() {
        List<User> users = dataStore.getAllUsers();
        userView.displayUsers(users);
    }
    public void updateUser() {
        String username = userView.promptForUsername();
        User user = dataStore.getUserByUsername(username);
        if (user != null) {
            String[] updatedDetails = userView.promptForUserDetails(user);
            updateUserDetails(user, updatedDetails);
            dataStore.saveUser(user);
            userView.displayMessage("User information updated successfully.");
        } else {
            userView.displayErrorMessage("User not found.");
        }
    }

    private void updateUserDetails(User user, String[] updatedDetails) {
        if (updatedDetails[0] != null && !updatedDetails[0].isEmpty()) {
            user.setName(updatedDetails[0]);
        }
        if (updatedDetails[1] != null && !updatedDetails[1].isEmpty()) {
            user.setSurname(updatedDetails[1]);
        }
        if (updatedDetails[2] != null && !updatedDetails[2].isEmpty()) {
            user.setSex(SEX.valueOf(updatedDetails[2].toUpperCase()));
        }
        if (updatedDetails[3] != null && !updatedDetails[3].isEmpty()) {
            user.setPhoneNumber(updatedDetails[3]);
        }
        if (updatedDetails[4] != null && !updatedDetails[4].isEmpty()) {
            user.setCitizenship(updatedDetails[4]);
        }
        if (updatedDetails[5] != null && !updatedDetails[5].isEmpty()) {
            user.setEmail(updatedDetails[5]);
        }

        if (user instanceof Student) {
            Student student = (Student) user;
            if (updatedDetails[7] != null && !updatedDetails[7].isEmpty()) {
                student.setMajor(updatedDetails[7]);
            }
            if (updatedDetails[8] != null && !updatedDetails[8].isEmpty()) {
                student.setType(STUDENTTYPE.valueOf(updatedDetails[8].toUpperCase()));
            }
            if (updatedDetails[9] != null && !updatedDetails[9].isEmpty()) {
                student.setYearOfStudy(Integer.parseInt(updatedDetails[9]));
            }
        } else if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            if (updatedDetails[7] != null && !updatedDetails[7].isEmpty()) {
                teacher.setDepartment(updatedDetails[7]);
            }
            if (updatedDetails[8] != null && !updatedDetails[8].isEmpty()) {
                teacher.setTeacherDegree(TEACHERDEGREE.valueOf(updatedDetails[8].toUpperCase()));
            }
            if (updatedDetails[9] != null && !updatedDetails[9].isEmpty()) {
                teacher.setSalary(Double.parseDouble(updatedDetails[9]));
            }
        } else if (user instanceof Employee) {
            Employee employee = (Employee) user;
            if (updatedDetails[7] != null && !updatedDetails[7].isEmpty()) {
                employee.setSalary(Double.parseDouble(updatedDetails[7]));
            }
        }
    }
    public void addCourse(Course course) {
    }

    public void updateCourse(String courseCode) {
    }

    public void removeCourse(String courseCode) {
    }

    public void viewCourse(String courseCode) {
    }

    public void addCourseSession(String courseCode) {
    }

    public void assignCourseToTeacher(String courseCode, int teacherId) {
    }

    public void viewCourseSchedule(String courseCode) {
    }

    public void viewStudentSchedule(String studentId) {
    }

    public void viewTeacherSchedule(int teacherId) {
    }
}