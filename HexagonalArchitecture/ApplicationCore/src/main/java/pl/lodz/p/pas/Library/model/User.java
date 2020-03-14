package pl.lodz.p.pas.Library.model;

public abstract class User {
    private String UUID;
    private boolean isActive;
    private PersonalData personalData;

    public User(PersonalData personalData) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = false;
        this.personalData = personalData;
    }

    public User(String firstName, String lastName, String userName) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = true;
        this.personalData = new PersonalData(firstName, lastName, userName);
    }

    public User(String firstName, String lastName, String userName, String password) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = true;
        this.personalData = new PersonalData(firstName, lastName, userName, password);
    }

    public User(String firstName, String lastName) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = true;
        this.personalData = new PersonalData(firstName, lastName);
    }

    public User() {}

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUUID() {
        return UUID;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getFirstName() {
        return personalData.getFirstName();
    }

    public String getLastName() {
        return personalData.getLastName();
    }

    public String getUserName() {
        return personalData.getUserName();
    }

    public String getPassword() {
        return personalData.getPassword();
    }

    public void setUserName(String userName) {
        personalData.setUserName(userName);
    }
}
