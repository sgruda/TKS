package pl.lodz.p.tks.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class UserEnt {
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public PersonalDataEnt getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataEnt personalData) {
        this.personalData = personalData;
    }

    private String UUID;
    private boolean isActive;
    private PersonalDataEnt personalData;

    public UserEnt(PersonalDataEnt personalData) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = false;
        this.personalData = personalData;
    }

    public UserEnt(String firstName, String lastName, String userName) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = true;
        this.personalData = new PersonalDataEnt(firstName, lastName, userName);
    }

    public UserEnt(String firstName, String lastName, String userName, String password) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = true;
        this.personalData = new PersonalDataEnt(firstName, lastName, userName, password);
    }

    public UserEnt(String firstName, String lastName) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.isActive = true;
        this.personalData = new PersonalDataEnt(firstName, lastName);
    }
    public String getUserName(){
        return this.personalData.getUserName();
    }
    public String getPassword(){
        return this.personalData.getPassword();
    }
}
