package data;

import model.academic.Course;
import model.academic.Mark;
import model.people.Employee;
import model.people.Student;
import model.people.Teacher;
import model.people.User;
import model.research.ResearchPaper;
import model.research.Researcher;

import java.util.List;

public interface DataStore {

    // User-related methods
    User getUserByUsername(String username);
    void saveUser(User user);

    // Student-related methods
    void saveStudent(Student student);
    Student getStudentById(String studentId);
    List<Student> getAllStudents();

    // Teacher-related methods
    void saveTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherId);
    List<Teacher> getAllTeachers();

    // Employee-related methods
    void saveEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();

    // Course-related methods
    void saveCourse(Course course);
    Course getCourseByCode(String courseCode);
    List<Course> getAllCourses();
    void addStudentToCourse(String studentId, String courseCode); // New method for enrollment
    void addMarkToStudent(String studentId, String courseCode, Mark mark); // New method for adding marks

    // Researcher-related methods
    void saveResearcher(Researcher researcher);
    Researcher getResearcherById(int researcherId);
    List<Researcher> getAllResearchers();
    void addResearchPaper(Researcher researcher, ResearchPaper paper);
    List<ResearchPaper> getResearchPapersByResearcher(Researcher researcher);
    List<ResearchPaper> getAllResearchPapers();

    // Data loading and saving
    void loadData();
    void saveData();
}