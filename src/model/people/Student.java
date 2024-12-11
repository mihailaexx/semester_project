package model.people;

import enums.SEX;
import enums.STUDENTDEGREE;
import enums.STUDENTTYPE;
import exceptions.CourseRegistrationException;
import model.academic.Course;
import model.academic.Mark;
import model.misc.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Student extends User implements Comparable<Person> {
    private School school;

    private String studentID;
    private STUDENTDEGREE degree;
    private STUDENTTYPE type;
    private double gpa;
    private int yearOfStudy;
    private Vector<Course> courses;
    private Vector<StudentOrganization> organizations;
    private HashMap<Course, Integer> retakes;

    private static final int MAX_CREDITS = 21;
    private static final int MAX_RETAKES = 3;

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
        this.courses = new Vector<>();
        this.organizations = new Vector<>();
        this.retakes = new HashMap<>();
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
    private int getTotalCurrentCredits() {
        return courses.stream().mapToInt(Course::getCredits).sum();
    }

    /**
     * Registers the student for a given course if constraints are met:
     * - total credits < 21
     * - failed courses (retakes) < 3
     * @param course
     */
    public void registerForCourse(Course course) throws CourseRegistrationException  {
        int currentCredits  = getTotalCurrentCredits();

        if (currentCredits + course.getCredits() > MAX_CREDITS) {
            throw new CourseRegistrationException("Cannot register for " + course.getName() + ": exceeding max credits.");
        }

        int timesRetaken = retakes.getOrDefault(course, 0);
        if (timesRetaken > MAX_RETAKES) {
            throw new CourseRegistrationException("Cannot register for " + course.getName() + ": exceeded max retakes.");
        }

        courses.add(course);
    }
    public void printCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    /**
     * Print marks for all enrolled courses
     */
    public void viewMarks() {
        if (courses.isEmpty()) {
            System.out.println("No courses enrolled.");
            return;
        }

        for (Course c : courses) {
            Mark m = c.getMarkForStudent(this);

            if (m != null) {
                System.out.println(c.getName() + " (" + c.getCode() + "): " + m);
            } else {
                System.out.println(c.getName() + " (" + c.getCode() + "): No marks yet.");
            }
        }
    }

    public void viewTranscript() {
        if (courses.isEmpty()) {
            System.out.println("No courses enrolled.");
            return;
        }

        double totalWeightedGrade = 0;
        int totalCredits = 0;

        System.out.println("Transcript for: " + getName() + " " + getSurname() + " (ID: " + studentID + ")");
        for (Course c : courses) {
            Mark m = c.getMarkForStudent(this);
            if (m == null) {
                continue;
            }

            double finalGrade = calcutaleFinalGrade(m);
            double gradePoint = gradeToGpa(finalGrade);

            System.out.printf("Course: %s (Credits: %d) Final Grade: %.2f (GPA: %.2f)%n",
                    c.getName(), c.getCredits(), finalGrade, gradePoint);

            totalWeightedGrade += gradePoint * c.getCredits();
            totalCredits += c.getCredits();

            // Check fail and increment retakes if finalGrade < 50
            if (finalGrade < 50) {
                retakes.put(c, retakes.getOrDefault(c, 0) + 1);
            }
        }

        if (totalCredits > 0) {
            gpa = totalWeightedGrade / totalCredits;
        } else {
            gpa = 0;
        }

        System.out.printf("Overall GPA: %.2f%n", gpa);
    }

    public void rateTeacher(Teacher teacher, int rating) {
        if (teacher != null) {
//            teacher.addRating(rating);
            System.out.println("You rated teacher " + teacher.getName() + " " + teacher.getSurname() +
                    " with " + rating + "points.");
        }
    }

    public Vector<StudentOrganization> getOrganizations() {
        return organizations;
    }
    public void joinOrganization(StudentOrganization organization) {
        if (!organizations.contains(organization)) {
            organizations.add(organization);
            organization.addMember(this);
        }
    }
    public void leaveOrganization(StudentOrganization organization) {
        if (organizations.remove(organization)) {
            organization.removeMember(this);
        }
    }
    public void printOrganizations() {
        for (StudentOrganization organization : organizations) {
            System.out.println(organization.getName());
        }
    }

    /**
     * Converts final marks into a final percentage grade.
     */
    private double calcutaleFinalGrade(Mark m) {
        double att1 = m.getAtt1();
        double att2 = m.getAtt2();
        double finalExam = m.getFinalExam();

        return att1 * 0.3 + att2 * 0.3 + finalExam * 0.4;
    }

    private double gradeToGpa(double finalGrade) {
        if (finalGrade >= 90) return 4.0;
        else if (finalGrade >= 80) return 3.3;
        else if (finalGrade >= 70) return 3.0;
        else if (finalGrade >= 60) return 2.3;
        else if (finalGrade >= 50) return 2.0;
        else return 0.0;
    }

    public int getTotalRetakes() {
        return retakes.values().stream().mapToInt(i -> i).sum();
    }
    @Override
    public int compareTo(@NotNull Person o) {
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getGpa(), student.getGpa()) == 0 &&
                getYearOfStudy() == student.getYearOfStudy() &&
                Objects.equals(getSchool(), student.getSchool()) &&
                Objects.equals(getStudentID(), student.getStudentID()) &&
                degree == student.degree &&
                type == student.type &&
                Objects.equals(getCourses(), student.getCourses()) &&
                Objects.equals(getOrganizations(), student.getOrganizations()) &&
                Objects.equals(retakes, student.retakes);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSchool(), getStudentID(), degree, getGpa(),
                getYearOfStudy(), type, getCourses(), getOrganizations(), retakes);
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