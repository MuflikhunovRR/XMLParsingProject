package ru.gotoqa.ParsingDom;

/**
 * @author Muflikhunov Roman
 */

public class ActorsModel {

    private Integer id;
    private String FirstName;
    private String LastName;
    private String BirthDay;

    public ActorsModel() {

    }

    @Override
    public String toString() {
        return "ActorsModel:: FirstName=" + this.FirstName + " LastName=" + this.LastName + " BirthDay=" + this.BirthDay;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public ActorsModel(Integer id, String firstName, String lastName, String birthDay) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        BirthDay = birthDay;
    }
}
