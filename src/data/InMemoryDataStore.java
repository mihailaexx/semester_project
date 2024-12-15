package data;

// ... other imports ...
import enums.SEX;
import enums.STUDENTDEGREE;
import enums.STUDENTTYPE;
import enums.TEACHERDEGREE;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.academic.Mark;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import model.research.ResearchPaper;
import model.research.Researcher;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryDataStore implements DataStore {

    private static final String DATA_FILE_PATH = "university_data.dat"; // Or separate files for each type

    private Map<String, User> users;
    private Map<String, Student> students;
    private Map<Integer, Teacher> teachers;
    private Map<Integer, Employee> employees;
    private Map<String, Course> courses;
    private Map<Integer, Researcher> researchers;
    private Map<Integer, ResearchPaper> researchPapers;

    public InMemoryDataStore() {
        users = new HashMap<>();
        students = new HashMap<>();
        teachers = new HashMap<>();
        employees = new HashMap<>();
        courses = new HashMap<>();
        researchers = new HashMap<>();
        researchPapers = new HashMap<>();
        // Load initial data from files (if available) or hardcode some initial data
        loadData();
    }

    private void initializeTestData() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            // Create sample courses
            Course course1 = new Course("CS101", "Introduction to Programming", 3,  1, 1);
            Course course2 = new Course("MA202", "Linear Algebra", 4, 2, 1);
            courses.put(course1.getCode(), course1);
            courses.put(course2.getCode(), course2);

            // Create sample students
            Student student1 = new Student("Aibek", "Kaibulla", SEX.MALE, dateFormat.parse("2006-03-10"), "87081234567", "KAZAKHSTAN", "aibekpassword123", "a_kaibulla@kbtu.kz", "IS", 2023, STUDENTDEGREE.BACHELOR, STUDENTTYPE.GRANT);
            Student student2 = new Student("Aliya", "Suleimen", SEX.FEMALE, dateFormat.parse("2005-07-18"), "87071234567", "KAZAKHSTAN", "password123", "a_suleimen@kbtu.kz", "IS", 2023, STUDENTDEGREE.BACHELOR, STUDENTTYPE.GRANT);
            students.put(student1.getStudentID(), student1);
            students.put(student2.getStudentID(), student2);

            // Create sample teachers
            Teacher teacher1 = new Teacher("Almas", "Atymtayev", SEX.MALE, dateFormat.parse("1985-09-25"), "a.atymtayev@kbtu.kz", "password123", "87771112233", "Kazakhstan", 5000.0, "Computer Science", TEACHERDEGREE.PROFESSOR);
            Teacher teacher2 = new Teacher("Dana", "Akhmetova", SEX.FEMALE, dateFormat.parse("1990-03-12"), "d.akhmetova@kbtu.kz", "password456", "87772223344", "Kazakhstan", 4500.0, "Computer Science", TEACHERDEGREE.ASSOCIATE_PROFESSOR);
            teachers.put(teacher1.getEmployeeId(), teacher1);
            teachers.put(teacher2.getEmployeeId(), teacher2);

            // Assign courses to teachers
            teacher1.addCourse(course1);
            teacher2.addCourse(course2);

            // Enroll students in courses
            student1.registerForCourse(course1);
            student2.registerForCourse(course2);

            // Add users to the users map
            users.put(student1.getUsername(), student1);
            users.put(student2.getUsername(), student2);
            users.put(teacher1.getUsername(), teacher1);
            users.put(teacher2.getUsername(), teacher2);

        } catch (ParseException | CourseRegistrationException e) {
            System.err.println("Error initializing test data: " + e.getMessage());
        }
    }
    // User-related methods
    @Override
    public User getUserByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Student-related methods
    @Override
    public void saveStudent(Student student) {
        students.put(student.getStudentID(), student);
        users.put(student.getUsername(), student);
    }

    @Override
    public Student getStudentById(String studentId) {
        return students.get(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    // Teacher-related methods
    @Override
    public void saveTeacher(Teacher teacher) {
        teachers.put(teacher.getEmployeeId(), teacher);
        users.put(teacher.getUsername(), teacher);
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        return teachers.get(teacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers.values());
    }

    // Employee-related methods
    @Override
    public void saveEmployee(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
        users.put(employee.getUsername(), employee);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employees.get(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    // Course-related methods
    @Override
    public void saveCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    @Override
    public Course getCourseByCode(String courseCode) {
        return courses.get(courseCode);
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
    @Override
    public void addStudentToCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            student.getCourses().add(course);
        }
    }


    @Override
    public void addMarkToStudent(String studentId, String courseCode, Mark mark) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            student.addMark(course, mark);
        }
    }

    // Researcher-related methods
    @Override
    public void saveResearcher(Researcher researcher) {
        researchers.put(researcher.getEmployeeId(), researcher);
        users.put(researcher.getUsername(), researcher);
    }

    @Override
    public Researcher getResearcherById(int researcherId) {
        return researchers.get(researcherId);
    }

    @Override
    public List<Researcher> getAllResearchers() {
        return new ArrayList<>(researchers.values());
    }


    @Override
    public void addResearchPaper(Researcher researcher, ResearchPaper paper) {
        researcher.addResearchPaper(paper);
        // If you need to maintain a separate list of all research papers:
        researchPapers.put(paper.hashCode(), paper); // Assuming ResearchPaper has a unique identifier
    }

    @Override
    public List<ResearchPaper> getResearchPapersByResearcher(Researcher researcher) {
        return researcher.getResearchPapers();
    }

    @Override
    public List<ResearchPaper> getAllResearchPapers() {
        List<ResearchPaper> allPapers = new ArrayList<>();
        for (Researcher researcher : researchers.values()) {
            allPapers.addAll(researcher.getResearchPapers());
        }
        return allPapers;
    }

    // Data loading and saving
    @Override
    public void loadData() {
        File dataFile = new File(DATA_FILE_PATH);
        if (!dataFile.exists()) {
            initializeTestData(); // Initialize only if file doesn't exist
            saveData(); // Save the initialized data to file
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            DataWrapper dataWrapper = (DataWrapper) ois.readObject();
            this.users = dataWrapper.getUsers();
            this.students = dataWrapper.getStudents();
            this.teachers = dataWrapper.getTeachers();
            this.employees = dataWrapper.getEmployees();
            this.courses = dataWrapper.getCourses();
            this.researchers = dataWrapper.getResearchers();
            this.researchPapers = dataWrapper.getResearchPapers();
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found. Starting with empty data.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH))) {
            DataWrapper dataWrapper = new DataWrapper(users, students, teachers, employees, courses, researchers, researchPapers);
            oos.writeObject(dataWrapper);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}