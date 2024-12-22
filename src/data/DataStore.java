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

    User getUserByUsername(String username);
    List<User> getAllUsers();
    void saveUser(User user);
    void saveStudent(Student student);
    Student getStudentById(String studentId);
    List<Student> getAllStudents();
    Schedule getStudentSchedule(String studentId);
    void saveTeacher(Teacher teacher);
    Teacher getTeacherById(String teacherId);
    List<Teacher> getAllTeachers();
    Schedule getTeacherSchedule(String teacherId);
    void saveEmployee(Employee employee);
    Employee getEmployeeById(String employeeId);
    List<Employee> getAllEmployees();
    void saveCourse(Course course);
    Course getCourseByCode(String courseCode);
    List<Course> getAllCourses();
    Schedule getCourseSchedule(String courseCode);
    public void removeCourse(Course course);
    void addStudentToCourse(String studentId, String courseCode);
    void addMarkToStudent(String studentId, String courseCode, Mark mark);// New method for adding marks
    void saveResearcher(Researcher researcher);
    Researcher getResearcherById(int researcherId);
    List<Researcher> getAllResearchers();
    void addResearchPaper(Researcher researcher, ResearchPaper paper);
    public void saveResearchPaper(ResearchPaper paper);
    ResearchPaper getResearchPaperById(int paperId);
    List<ResearchPaper> getAllResearchPapers();
    void saveResearchProject(ResearchProject project);
    ResearchProject getResearchProjectById(int projectId);
    List<ResearchProject> getAllResearchProjects();
    void addCourseSession(String courseCode, LESSON_TYPE lessonType, DayOfWeek day, LocalTime time);
    void saveMessage(Message message);
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
    void loadData();
    void saveData();
}