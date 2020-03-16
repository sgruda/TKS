package pl.lodz.p.tks.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClientEnt extends UserEnt {
    public ClientEnt(PersonalDataEnt personalData) {
        super(personalData);
    }

    public ClientEnt(String firstName, String lastName, String userName, String password) {
        super( firstName,  lastName,  userName, password);
    }
}
