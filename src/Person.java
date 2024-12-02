import java.util.Date;
import java.util.Objects;

public abstract class Person implements Comparable<Person>, Cloneable {
    private String ID; // ИИН
    private String name;
    private String surname;
    private SEX sex;
    private Date birthDate;
    private String phoneNumber;
    private String citizenship;


    public Person(String ID, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship) {
        if (ID.substring(0, 6).equals(String.valueOf(birthDate.getYear()).substring(2,4)+String.format("%02d", birthDate.getMonth())+String.format("%02d", birthDate.getDate()))
                && ID.length() == 12
                && ID.matches("[0-9]+")
//                && ( (ID.substring(7,7).equals("0") && citizenship.equals("Kazakhstan")) || (ID.substring(7,7).equals("6") && sex.equals("female") && birthDate.getYear()>=2000) || (ID.substring(7,7).equals("5") && sex.equals("male") && birthDate.getYear()>=2000) || (ID.substring(7,7).equals("4") && sex.equals("female") && birthDate.getYear()>=1900) || (ID.substring(7,7).equals("3") && sex.equals("female") && birthDate.getYear()>=1900))
        ) {
            this.ID = ID;
        } else {
            throw new IllegalArgumentException("invalid ID");
        }
        this.name = name;
        this.surname = surname;
        this.sex = sex;
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
    public SEX getSex() {
        return sex;
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
    public int compareTo(Person o) {
        return this.birthDate.compareTo(o.birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(ID, person.ID) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(sex, person.sex) && Objects.equals(birthDate, person.birthDate) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(citizenship, person.citizenship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, surname, sex, birthDate, phoneNumber, citizenship);
    }

    @Override
    public String toString() {
        return "Person[" +
                "ID=" + ID +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ']';
    }

    @Override
    public Person clone() {
        try {
            Person clone = (Person) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

enum SEX {
    male,
    female
}