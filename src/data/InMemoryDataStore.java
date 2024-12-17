package data;

// ... other imports ...
import enums.*;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.academic.Mark;
import model.academic.Schedule;
import model.manager.OrManager;
import model.misc.Message;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import model.research.ResearchPaper;
import model.research.Researcher;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryDataStore implements DataStore {

    private static final String DATA_FILE_PATH = "university2_data.dat";

    private Map<String, User> users;
    private Map<String, Student> students;
    private Map<Integer, Teacher> teachers;
    private Map<Integer, Employee> employees;
    private Map<Integer, OrManager> orManagers;
    private Map<String, Course> courses;
    private Map<Integer, Researcher> researchers;
    private Map<Integer, ResearchPaper> researchPapers;
    private Map<String, List<Message>> userMessages;

    public InMemoryDataStore() {
        users = new HashMap<>();
        students = new HashMap<>();
        teachers = new HashMap<>();
        employees = new HashMap<>();
        courses = new HashMap<>();
        researchers = new HashMap<>();
        researchPapers = new HashMap<>();
        userMessages = new HashMap<>();
        orManagers = new HashMap<>();
//        System.out.println("Data file path: " + new File(DATA_FILE_PATH).getAbsolutePath());

        // Load initial data from files (if available) or hardcode some initial data
        loadData();
    }

    private void initializeTestData() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Create sample courses
            Course course1 = new Course("CSCI1103", "Programming Principles 1",6, "SITE");
            Course course2 = new Course("MATH1102", "Calculus 1", 5, "SHPM");
            Course course3 = new Course("CSCI1102", "Discrete Structures", 5, "SITE");
            Course course4 = new Course("CSCI2106", "OOP", 5,  "SITE");
            courses.put(course1.getCode(), course1);
            courses.put(course2.getCode(), course2);
            courses.put(course3.getCode(), course3);
            courses.put(course4.getCode(), course4);

            // Create sample students
            Student student1 = new Student("Aibek", "Kaibulla", SEX.MALE, dateFormat.parse("2006-03-10"), "87081234567", "KAZAKHSTAN", "aibekpassword123", "a_kaibulla@kbtu.kz", "IS", 2023, STUDENTDEGREE.BACHELOR, STUDENTTYPE.GRANT);
            Student student2 = new Student("Mikhail", "Nepomnu", SEX.MALE, dateFormat.parse("2005-07-18"), "87071234567", "KAZAKHSTAN", "password123", "m_nepomnu@kbtu.kz", "IS", 2023, STUDENTDEGREE.BACHELOR, STUDENTTYPE.GRANT);
            Student student3 = new Student("Djaklin", "Nepomnu1", SEX.FEMALE, dateFormat.parse("2005-08-18"), "87071234568", "KAZAKHSTAN", "password123", "d_nepomnu@kbtu.kz", "IS", 2023, STUDENTDEGREE.BACHELOR, STUDENTTYPE.GRANT);
            Student student4 = new Student("Aliya", "Suleimen", SEX.FEMALE, dateFormat.parse("2005-07-18"), "87071234567", "KAZAKHSTAN", "password123", "a_suleimen@kbtu.kz", "IS", 2023, STUDENTDEGREE.BACHELOR, STUDENTTYPE.GRANT);
            students.put(student1.getStudentID(), student1);
            students.put(student2.getStudentID(), student2);
            students.put(student3.getStudentID(), student3);
            students.put(student4.getStudentID(), student4);

            // Create sample teachers
            Teacher teacher1 = new Teacher("Assylzhan", "Izbassar", SEX.MALE, dateFormat.parse("2000-01-01"), "a_izbassar@kbtu.kz", "password123", "87771112233", "Kazakhstan", 5000.0, "SITE", TEACHERDEGREE.LECTURER);
            Teacher teacher2 = new Teacher("Pakizar", "Shamoi", SEX.FEMALE, dateFormat.parse("1990-01-01"), "p_shamoi@kbtu.kz", "password123", "87771112233", "Kazakhstan", 8000.0, "SITE", TEACHERDEGREE.PROFESSOR);
            Teacher teacher3 = new Teacher("Tamiris", "Abdildaeva", SEX.FEMALE, dateFormat.parse("2000-01-01"), "t_abdildaeva@kbtu.kz", "password456", "87772223344", "Kazakhstan", 4500.0, "SITE", TEACHERDEGREE.TUTOR);
            teachers.put(teacher1.getEmployeeId(), teacher1);
            employees.put(teacher1.getEmployeeId(), teacher1);
            teachers.put(teacher2.getEmployeeId(), teacher2);
            employees.put(teacher2.getEmployeeId(), teacher2);
            teachers.put(teacher3.getEmployeeId(), teacher3);
            employees.put(teacher3.getEmployeeId(), teacher3);


            OrManager orManager = new OrManager("OR", "Manager", SEX.FEMALE, dateFormat.parse("1980-05-20"), "a_madina@kbtu.kz", "password123", "87771234567", "KAZAKHSTAN", 5000.0);
            employees.put(orManager.getEmployeeId(), orManager);
            users.put(orManager.getUsername(), orManager);
            orManagers.put(orManager.getEmployeeId(), orManager);

            // Assign courses to teachers
            teacher1.addCourse(course4);
            teacher2.addCourse(course4);
            teacher3.addCourse(course4);
            teacher3.addCourse(course2);

            // Enroll students in courses
            student1.registerForCourse(course4);
            student2.registerForCourse(course4);
            student3.registerForCourse(course4);

            // Add users to the users map
            users.put(student1.getUsername(), student1);
            users.put(student2.getUsername(), student2);
            users.put(student3.getUsername(), student3);
            users.put(student4.getUsername(), student4);

            users.put(teacher1.getUsername(), teacher1);
            users.put(teacher2.getUsername(), teacher2);
            users.put(teacher3.getUsername(), teacher3);


        } catch (ParseException | CourseRegistrationException e) {
            System.err.println("Error initializing test data: " + e.getMessage());
        }
    }
    // User-related methods
    @Override
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Student-related methods
    @Override
    public void saveStudent(Student student) {
        students.put(student.getStudentID(), student);
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
            student.getEnrolledCourses().add(course);
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

    @Override
    public void addCourseSession(String courseCode, LESSON_TYPE lessonType, DayOfWeek day, LocalTime time) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            if (course.getSchedule() == null) {
                course.setSchedule(new Schedule());
            }
            course.getSchedule().addCourseSession(course, lessonType, day, time);
            saveCourse(course);
        } else {
            System.err.println("Course not found: " + courseCode);
        }
    }

    @Override
    public Schedule getCourseSchedule(String courseCode) {
        Course course = getCourseByCode(courseCode);
        return (course != null) ? course.getSchedule() : null;
    }

    @Override
    public Schedule getStudentSchedule(String studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            Schedule schedule = new Schedule();
            for (Course course : student.getEnrolledCourses()) {
                Schedule courseSchedule = course.getSchedule();
                if (courseSchedule != null) {
                    mergeSchedules(schedule, courseSchedule);
                }
            }
            return schedule;
        }
        return null;
    }

    @Override
    public Schedule getTeacherSchedule(int teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        if (teacher != null) {
            Schedule schedule = new Schedule();
            for (Course course : teacher.getCourses()) {
                Schedule courseSchedule = course.getSchedule();
                if (courseSchedule != null) {
                    mergeSchedules(schedule, courseSchedule);
                }
            }
            return schedule;
        }
        return null;
    }

    private void mergeSchedules(Schedule targetSchedule, Schedule sourceSchedule) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getValue() > Schedule.NUMBER_OF_WORKING_DAYS) break;
            Map<LocalTime, Schedule.ScheduledClass> targetDaySchedule = targetSchedule.getScheduleForDay(day);
            Map<LocalTime, Schedule.ScheduledClass> sourceDaySchedule = sourceSchedule.getScheduleForDay(day);

            for (int hour = Schedule.START_HOUR; hour < Schedule.END_HOUR; hour++) {
                LocalTime time = LocalTime.of(hour, 0);
                Schedule.ScheduledClass sourceSession = sourceDaySchedule.get(time);
                if (sourceSession != null) {
                    targetDaySchedule.put(time, sourceSession);
                }
            }
        }
    }

    @Override
    public void saveMessage(Message message) {
        String recipientUsername = message.getRecipient().getUsername();
        userMessages.computeIfAbsent(recipientUsername, k -> new ArrayList<>()).add(message);
    }

    @Override
    public List<Message> getMessagesForUser(User user) {
        return userMessages.getOrDefault(user.getUsername(), new ArrayList<>());
    }
    @Override
    public User getUserByUsername(String username) {
        User user = users.get(username);
        return user;
//        if (user != null) {
//            // Create a new instance of User or its subclass with necessary details
//            if (user instanceof Student) {
//                Student student = (Student) user;
//                return new Student(student.getName(), student.getSurname(), student.getSex(), student.getBirthDate(),
//                        student.getPhoneNumber(), student.getCitizenship(), null, student.getEmail(),
//                        student.getMajor(), student.getYearOfStudy(), student.getDegree(), student.getType());
//            } else if (user instanceof Teacher) {
//                Teacher teacher = (Teacher) user;
//                return new Teacher(teacher.getName(), teacher.getSurname(), teacher.getSex(), teacher.getBirthDate(),
//                        teacher.getEmail(), null, teacher.getPhoneNumber(), teacher.getCitizenship(), teacher.getSalary(),
//                        teacher.getDepartment(), teacher.getTeacherDegree());
//            }
//            // ... handle other user types ...
//        }
//        return null;
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