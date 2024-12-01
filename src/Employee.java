import java.util.Date;

public abstract class Employee extends User {
    private static int employeeID = 1;
    private double salary;

    public Employee(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship, String password, double salary) {
        super(ID, name, surname, sex, birthDate, phoneNumber, citizenship, password);
        this.salary = salary;
        super.email = name.charAt(0) + "." + surname + "@kbtu.kz";
        employeeID++;
    }
}