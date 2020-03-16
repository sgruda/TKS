package pl.lodz.p.tks.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LibraryOwnerEnt extends UserEnt {
    public LibraryOwnerEnt(PersonalDataEnt personalData) {
        super(personalData);
    }

    public LibraryOwnerEnt(String firstName, String lastName, String userName, String password) {
        super( firstName,  lastName,  userName, password);
    }
}
