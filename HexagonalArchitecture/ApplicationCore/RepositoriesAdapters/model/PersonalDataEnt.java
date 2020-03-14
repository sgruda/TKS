package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonalDataEnt {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public PersonalDataEnt(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public PersonalDataEnt(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public PersonalDataEnt(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = firstName.charAt(0) + " " + lastName;
    }
}
