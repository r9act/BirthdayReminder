package model;

import java.time.LocalDate;

public class Person {

    private LocalDate birthDate;
    private String name;

    public Person(LocalDate birthDate, String name) {
        this.birthDate = birthDate;
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " has his/her birthday on " +
                birthDate.getMonth() + " " + birthDate.getDayOfMonth();
    }
}

