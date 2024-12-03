import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Student extends User {
    private School school;

    private String studentID;
    private STUDENTDEGREE degree;
    private double gpa;
    private int year_of_study;
    private Vector<Course> courses;
    private Vector<StudentOrganization> organizations;

    public void sync(University university) {
        university.addStudent(this);
    }

    public Student(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, String studentID, School school, STUDENTDEGREE degree, int year_of_study) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password);
        this.studentID = studentID;
        this.school = school; school.addStudent(this);
        this.degree = degree;
        this.year_of_study = year_of_study;
        super.email = name.charAt(0) + "_" + surname + "@kbtu.kz";
        this.courses = new Vector<Course>();
        this.organizations = new Vector<StudentOrganization>();
    }

    public School getSchool() {
        return school;
    }

    public Vector<Course> getCourses() {
        return courses;
    }
    public void viewCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void registerToCourse(Course course) {
        courses.add(course);
    }

    public double getGpa() {
        return gpa;
    }

    public void joinOrganization(StudentOrganization organization) {
        organizations.add(organization);
    }

    public void leaveOrganization(StudentOrganization organization) {
        organizations.remove(organization);
    }

    public void viewOrganizations() {
        for (StudentOrganization organization : organizations) {
            System.out.println(organization);
        }
    }

    public void viewTranscript() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(getGpa(), student.getGpa()) == 0 && year_of_study == student.year_of_study && Objects.equals(school, student.school) && Objects.equals(studentID, student.studentID) && degree == student.degree && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), school, studentID, degree, getGpa(), year_of_study, courses);
    }

    @Override
    public String toString() {
        return "Student[" + super.toString() +
                "school=" + school +
                ", studentID='" + studentID + '\'' +
                ", degree=" + degree +
                ", gpa=" + gpa +
                ", year_of_study=" + year_of_study +
                ", courses=" + courses +
                ']';
    }
}

enum STUDENTDEGREE {
    BACHELOR,
    MASTER,
    DOCTORAL
}