package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class UserEnt {
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
}
