import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import enums.SEX;
import enums.STUDENTDEGREE;
import enums.STUDENTTYPE;

public class Student extends User {
    private School school;

    private String studentID;
    private STUDENTDEGREE degree;
    private STUDENTTYPE type;
    private double gpa;
    private int yearOfStudy;
    private Vector<Course> courses;
    private Vector<StudentOrganization> organizations;
    private HashMap<Discipline, Integer> retakes;

    public void sync(University university) {
        university.addStudent(this);
    }

    public Student(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, String studentID, School school, STUDENTDEGREE degree, int yearOfStudy, STUDENTTYPE type) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password);
        this.studentID = studentID;
        this.school = school; school.addStudent(this);
        this.degree = degree;
        this.yearOfStudy = yearOfStudy;
        super.email = name.charAt(0) + "_" + surname + "@kbtu.kz";
        this.type = type;
        this.courses = new Vector<Course>();
        this.organizations = new Vector<StudentOrganization>();
        this.retakes = new HashMap<Discipline, Integer>();
    }

    public School getSchool() {
        return school;
    }
    public String getStudentID() {
        return studentID;
    }
    public double getGpa() {
        return gpa;
    }
    public int getYearOfStudy() {
        return yearOfStudy;
    }
    public Vector<Course> getCourses() {
        return courses;
    }

    public void registerForCourse(Course course) {
        // create request for OR manager
        // Check credits < 21, failedCoursesCount < 3
        courses.add(course);
    }
    public void printCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void viewMarks() {
        // Implementation
    }

    public void viewTranscript() {
        // Implementation
    }

    public void rateTeacher(Teacher teacher, int rating) {
        // Implementation
    }

    public Vector<StudentOrganization> getOrganizations() {
        return organizations;
    }
    public void joinOrganization(StudentOrganization organization) {
        organizations.add(organization);
    }
    public void leaveOrganization(StudentOrganization organization) {
        organizations.remove(organization);
    }
    public void printOrganizations() {
        for (StudentOrganization organization : organizations) {
            System.out.println(organization);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getGpa(), student.getGpa()) == 0 && getYearOfStudy() == student.getYearOfStudy() && Objects.equals(getSchool(), student.getSchool()) && Objects.equals(getStudentID(), student.getStudentID()) && degree == student.degree && type == student.type && Objects.equals(getCourses(), student.getCourses()) && Objects.equals(getOrganizations(), student.getOrganizations()) && Objects.equals(retakes, student.retakes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSchool(), getStudentID(), degree, getGpa(), getYearOfStudy(), type, getCourses(), getOrganizations(), retakes);
    }

    @Override
    public String toString() {
        return "Student[[" + super.toString() +
                "], school=" + school +
                ", studentID='" + studentID + '\'' +
                ", degree=" + degree +
                ", gpa=" + gpa +
                ", yearOfStudy=" + yearOfStudy +
                ", type=" + type +
                ", courses=" + courses +
                ", organizations=" + organizations +
                ", retakes=" + retakes +
                ']';
    }
}