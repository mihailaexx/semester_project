import java.util.Date;

public abstract class Person {
    private String ID;
    private String name;
    private String surname;
    private String sex;
    private int age;
    private Date birthDate;
    private String phoneNumber;
    private String citizenship;


    public Person(String ID, String name, String surname, String sex, int age, Date birthDate, String phoneNumber, String citizenship) {
        if (ID.substring(0, 1).equals(birthDate.getYear()))
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.citizenship = citizenship;
    }

//    public String getID() {
//        return ID;
//    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
//    public Date getBirthDate() {
//        return birthDate;
//    }
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//    public String getCitizenship() {
//        return citizenship;
//    }

    @Override
    public String toString() {
        return "Person[" +
                "ID=" + ID +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ']';
    }
}
