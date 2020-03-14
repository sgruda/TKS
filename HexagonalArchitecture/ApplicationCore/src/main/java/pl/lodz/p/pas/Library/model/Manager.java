package pl.lodz.p.pas.Library.model;

public class Manager extends User {

    public Manager(PersonalData personalData) {
        super(personalData);
    }

    public Manager(String firstName, String lastName, String userName,String password) {
        super( firstName,  lastName,  userName, password);
    }

    public Manager() {
    }
}