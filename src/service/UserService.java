package service;

import data.DataStore;
import enums.TEACHERDEGREE;
import exceptions.AuthenticationException;
import exceptions.CourseRegistrationException;
import exceptions.InvalidInputException;
import model.manager.OrManager;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
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
            Date birthDate = dateFormat.parse(details[11]);

            int yearOfStudy = Integer.parseInt(details[10]);
            STUDENTTYPE studentType = STUDENTTYPE.valueOf(details[9].toUpperCase());
            String email = details[1].toLowerCase().charAt(0) + "_" + details[2].toLowerCase() + "@kbtu.kz";

            Student student = new Student(
                    details[1], // first name
                    details[2], // last name
                    SEX.valueOf(details[3].toUpperCase()),
                    birthDate,
                    details[4], // phone number
                    details[5], // citizenship
                    details[7], // password
                    email, // email
                    details[8], // major
                    yearOfStudy,
                    STUDENTDEGREE.BACHELOR, // student degree
                    studentType // student type
            );
            dataStore.saveUser(student);
            dataStore.saveStudent(student);
        } catch (ParseException e) {
            throw new InvalidInputException("Invalid date format for birth date. Use yyyy-MM-dd.", e);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format. Check year of study and other numeric fields.", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid input: " + e.getMessage(), e);
        }
    }

    public void createTeacher(String[] details) throws InvalidInputException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(details[11]);

            Teacher teacher = new Teacher(
                    details[1],  // first name
                    details[2],  // last name
                    SEX.valueOf(details[3].toUpperCase()),
                    birthDate,
                    details[6],  // email
                    details[7],  // password
                    details[4],  // phone number
                    details[5],  // citizenship
                    Double.parseDouble(details[10]), // salary
                    details[8],  // department
                    TEACHERDEGREE.valueOf(details[9].toUpperCase()) // teacher degree
            );
            dataStore.saveUser(teacher);
            dataStore.saveTeacher(teacher);
        } catch (ParseException e) {
            throw new InvalidInputException("Invalid date format for birth date. Use yyyy-MM-dd.", e);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format for salary.", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid input: " + e.getMessage(), e);
        }
    }
    public void registerStudentForCourse(Student student, String courseCode) {
        Course course = dataStore.getCourseByCode(courseCode);
        if (course != null) {
            try {
                student.registerForCourse(course);
                dataStore.saveStudent(student);
            } catch (CourseRegistrationException e) {
                System.err.println("Error registering student for course: " + e.getMessage());
            }
        } else {
            System.err.println("Course not found: " + courseCode);
        }
    }

    public void createEmployee(String[] details) throws InvalidInputException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(details[9]);

            Employee employee = new Employee(
                    details[1], // first name
                    details[2], // last name
                    SEX.valueOf(details[3].toUpperCase()),
                    birthDate,
                    details[4], // email
                    details[5], // password
                    details[6], // phone number
                    details[7], // citizenship
                    Double.parseDouble(details[8]) // salary
            ) {};
            dataStore.saveUser(employee);
            dataStore.saveEmployee(employee);
        } catch (ParseException e) {
            throw new InvalidInputException("Invalid date format for birth date. Use yyyy-MM-dd.", e);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format for salary.", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid input: " + e.getMessage(), e);
        }
    }

    public void createOrManager(String[] details) throws InvalidInputException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(details[9]);

            OrManager orManager = new OrManager(
                    details[1], // first name
                    details[2], // last name
                    SEX.valueOf(details[3].toUpperCase()),
                    birthDate,
                    details[4], // email
                    details[5], // password
                    details[6], // phone number
                    details[7], // citizenship
                    Double.parseDouble(details[8]) // salary
            );

            dataStore.saveEmployee(orManager);
            dataStore.saveUser(orManager);
            dataStore.saveOrManager(orManager);
        } catch (ParseException e) {
            throw new InvalidInputException("Invalid date format for birth date. Use yyyy-MM-dd.", e);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format. Check salary.", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid input: " + e.getMessage(), e);
        }
    }
}