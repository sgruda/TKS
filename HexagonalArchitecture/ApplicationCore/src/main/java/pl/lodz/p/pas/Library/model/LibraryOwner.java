package pl.lodz.p.pas.Library.model;

public class LibraryOwner extends User {
    public LibraryOwner(PersonalData personalData) {
        super(personalData);
    }

    public LibraryOwner(String firstName, String lastName, String userName,String password) {
        super( firstName,  lastName,  userName, password);
    }

    public LibraryOwner() {
    }
}
