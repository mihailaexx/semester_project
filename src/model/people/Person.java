package model.people;

import enums.SEX;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private SEX sex;
    private Date birthDate;

    public Person(String name, String surname, SEX sex, Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public SEX getSex() {
        return sex;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setSex(SEX sex) { this.sex = sex; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                sex == person.sex &&
                Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, sex, birthDate);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                '}';
    }
}