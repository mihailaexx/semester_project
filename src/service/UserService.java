package service;

import data.DataStore;
import exceptions.AuthenticationException;
import exceptions.CourseRegistrationException;
import exceptions.InvalidInputException;
import model.people.Student;
import model.people.User;
import model.academic.Course;
import enums.SEX;
import enums.STUDENTDEGREE;
import enums.STUDENTTYPE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserService {
    private final DataStore dataStore;

    public UserService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public User authenticateUser(String username, String password) throws AuthenticationException {
        User user = dataStore.getUserByUsername(username);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        throw new AuthenticationException("Invalid username or password.");
    }

    public void createStudent(String[] details) throws InvalidInputException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(details[10]);

            int yearOfStudy = Integer.parseInt(details[9]);
            STUDENTTYPE studentType = STUDENTTYPE.valueOf(details[8].toUpperCase());

            String email = details[0].toLowerCase().charAt(0) + "_" + details[1].toLowerCase() + "@kbtu.kz";

            Student student = new Student(
                    details[0], // first name
                    details[1], // last name
                    SEX.valueOf(details[2].toUpperCase()),
                    birthDate,
                    details[3], // phone number
                    details[4], // citizenship
                    details[6], // password
                    email, // email
                    details[7], // major
                    yearOfStudy,
                    STUDENTDEGREE.BACHELOR, // student degree
                    studentType // student type
            );

            dataStore.saveStudent(student);
        } catch (ParseException e) {
            throw new InvalidInputException("Invalid date format for birth date. Use yyyy-MM-dd.", e);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format. Check year of study and other numeric fields.", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid input: " + e.getMessage(), e);
        }
    }

    public void registerStudentForCourse(Student student, String courseCode) {
        Course course = dataStore.getCourseByCode(courseCode);
        if (course != null) {
            try {
                student.registerForCourse(course);
                dataStore.saveStudent(student); // Update the student in the data store
            } catch (CourseRegistrationException e) {
                // Handle exceptions, e.g., course registration limits
                System.err.println("Error registering student for course: " + e.getMessage());
            }
        } else {
            System.err.println("Course not found: " + courseCode);
        }
    }
}