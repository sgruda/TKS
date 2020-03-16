package pl.lodz.p.pas.Library.model;

public class Client extends User {
    public Client(PersonalData personalData) {
        super(personalData);
    }

    public Client(String firstName, String lastName, String userName,String password) {
        super( firstName,  lastName,  userName, password);
    }

    public Client() {
    }
}
