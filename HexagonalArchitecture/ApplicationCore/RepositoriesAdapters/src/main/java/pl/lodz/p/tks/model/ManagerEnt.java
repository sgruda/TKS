package pl.lodz.p.tks.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ManagerEnt extends UserEnt {

    public ManagerEnt(PersonalDataEnt personalData) {
        super(personalData);
    }

    public ManagerEnt(String firstName, String lastName, String userName, String password) {
        super( firstName,  lastName,  userName, password);
    }
}