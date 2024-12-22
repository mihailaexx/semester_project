package data;

import enums.LESSON_TYPE;
import model.academic.Course;
import model.academic.Mark;
import model.academic.Schedule;
import model.manager.OrManager;
import model.manager.Request;
import model.misc.Message;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import model.research.Researcher;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface DataStore {

    // User-related methods

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    User getUserByUsername(String username);

    /**
     * Retrieves a list of all users in the data store.
     *
     * @return A list of all User objects.
     */
    List<User> getAllUsers();
    /**
     * Saves a user to the data store.
     *
     * @param user The User object to save.
     */
    void saveUser(User user);

    // Student-related methods

    /**
     * Saves a student to the data store.
     *
     * @param student The Student object to save.
     */
    void saveStudent(Student student);

    /**
     * Retrieves a student by their ID.
     *
     * @param studentId The ID of the student to retrieve.
     * @return The Student object if found, null otherwise.
     */
    Student getStudentById(String studentId);

    /**
     * Retrieves a list of all students in the data store.
     *
     * @return A list of all Student objects.
     */
    List<Student> getAllStudents();

    /**
     * Retrieves the schedule for a given student.
     *
     * @param studentId The ID of the student.
     * @return The Schedule object for the student, or null if the student is not found or has no schedule.
     */
    Schedule getStudentSchedule(String studentId);

    // Teacher-related methods

    /**
     * Saves a teacher to the data store.
     *
     * @param teacher The Teacher object to save.
     */
    void saveTeacher(Teacher teacher);

    /**
     * Retrieves a teacher by their ID.
     *
     * @param teacherId The ID of the teacher to retrieve.
     * @return The Teacher object if found, null otherwise.
     */
    Teacher getTeacherById(String teacherId);

    /**
     * Retrieves a list of all teachers in the data store.
     *
     * @return A list of all Teacher objects.
     */
    List<Teacher> getAllTeachers();

    /**
     * Retrieves the schedule for a given teacher.
     *
     * @param teacherId The ID of the teacher.
     * @return The Schedule object for the teacher, or null if the teacher is not found or has no schedule.
     */
    Schedule getTeacherSchedule(String teacherId);

    // Employee-related methods

    /**
     * Saves an employee to the data store.
     *
     * @param employee The Employee object to save.
     */
    void saveEmployee(Employee employee);

    /**
     * Retrieves an employee by their ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return The Employee object if found, null otherwise.
     */
    Employee getEmployeeById(String employeeId);

    /**
     * Retrieves a list of all employees in the data store.
     *
     * @return A list of all Employee objects.
     */
    List<Employee> getAllEmployees();

    // Course-related methods

    /**
     * Saves a course to the data store.
     *
     * @param course The Course object to save.
     */
    void saveCourse(Course course);

    /**
     * Retrieves a course by its code.
     *
     * @param courseCode The code of the course to retrieve.
     * @return The Course object if found, null otherwise.
     */
    Course getCourseByCode(String courseCode);

    /**
     * Retrieves a list of all courses in the data store.
     *
     * @return A list of all Course objects.
     */
    List<Course> getAllCourses();

    /**
     * Retrieves the schedule for a given course.
     *
     * @param courseCode The code of the course.
     * @return The Schedule object for the course, or null if the course is not found or has no schedule.
     */
    Schedule getCourseSchedule(String courseCode);
    public void removeCourse(Course course);

    /**
     * Adds a student to a course.
     *
     * @param studentId  The ID of the student to add.
     * @param courseCode The code of the course to add the student to.
     */
    void addStudentToCourse(String studentId, String courseCode);

    /**
     * Adds a mark to a student for a specific course.
     *
     * @param studentId  The ID of the student.
     * @param courseCode The code of the course.
     * @param mark       The Mark object to add.
     */
    void addMarkToStudent(String studentId, String courseCode, Mark mark);// New method for adding marks

    // Researcher-related methods

    /**
     * Saves a researcher to the data store.
     *
     * @param researcher The Researcher object to save.
     */
    void saveResearcher(Researcher researcher);

    /**
     * Retrieves a researcher by their ID.
     *
     * @param researcherId The ID of the researcher to retrieve.
     * @return The Researcher object if found, null otherwise.
     */
    Researcher getResearcherById(int researcherId);

    /**
     * Retrieves a list of all researchers in the data store.
     *
     * @return A list of all Researcher objects.
     */
    List<Researcher> getAllResearchers();

    /**
     * Adds a research paper to a researcher.
     *
     * @param researcher The Researcher object to add the paper to.
     * @param paper      The ResearchPaper object to add.
     */
    void addResearchPaper(Researcher researcher, ResearchPaper paper);
    public void saveResearchPaper(ResearchPaper paper);

        /**
         * Retrieves a list of research papers for a given researcher.
         *
         * @param paperId id of Researcher
         * @return A list of ResearchPaper objects.
         */
    ResearchPaper getResearchPaperById(int paperId);

    /**
     * Retrieves a list of all research papers in the data store.
     *
     * @return A list of all ResearchPaper objects.
     */
    List<ResearchPaper> getAllResearchPapers();

    void saveResearchProject(ResearchProject project);
    ResearchProject getResearchProjectById(int projectId);
    List<ResearchProject> getAllResearchProjects();
    /**
     * Adds a course session to the schedule of a specific course.
     *
     * @param courseCode The code of the course to add the session to.
     * @param lessonType The type of the lesson (e.g., LECTURE, PRACTICE, LAB).
     * @param day        The day of the week for the session.
     * @param time       The start time of the session.
     */
    void addCourseSession(String courseCode, LESSON_TYPE lessonType, DayOfWeek day, LocalTime time);

    /**
     * Saves a message to the data store.
     *
     * @param message The Message object to save.
     */
    void saveMessage(Message message);

    /**
     * Retrieves all messages for a specific user.
     *
     * @param user The User object for whom to retrieve messages.
     * @return A list of Message objects.
     */
    List<Message> getMessagesForUser(User user);

    void addRegistrationRequest(Request request);
    Request getRequestById(int requestId);
    List<Request> getAllRequests();
    void updateStudent(Student student);
    void updateCourse(Course course);
    void saveRequest(Request request);

     void saveOrManager(OrManager orManager);
     OrManager getOrManagerByUsername(String username);
     List<OrManager> getAllOrManagers();
    // Data loading and saving
    /**
     * Loads data from a persistent storage into memory.
     * This method should handle any necessary setup or initialization
     * for data structures used to store the in-memory data.
     * If no data file is found, it should initialize with empty data or
     * default values.
     */
    void loadData();

    /**
     * Saves data from memory to a persistent storage.
     *
     * This method should handle the serialization and writing of data
     * to a file in a way that can be later loaded by `loadData()`.
     */
    void saveData();
}