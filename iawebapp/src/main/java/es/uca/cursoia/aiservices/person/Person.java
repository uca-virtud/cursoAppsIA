package es.uca.cursoia.aiservices.person;

import dev.langchain4j.model.output.structured.Description;

import java.time.LocalDate;

public class Person {

    @Description("first name of a person")
    // you can add an optional description to help an LLM have a better understanding
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String street;
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


}