package pl.lodz.p.pas.Library.repositories;

import pl.lodz.p.tks.model.ClientEnt;
import pl.lodz.p.tks.model.LibraryOwnerEnt;
import pl.lodz.p.tks.model.ManagerEnt;
import pl.lodz.p.tks.model.UserEnt;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository {
    private List<UserEnt> userList = new ArrayList<>();

    public synchronized List<UserEnt> getAllUsers() {
        return new ArrayList<>(userList);
    }

    public synchronized void addUser(UserEnt user) {
        this.userList.add(user);
    }

    public synchronized UserEnt getUser(UserEnt user) {
        try {
            if (userList.contains(user)) {
                return user;
            }
        } catch (Exception e) {
            System.out.println("User doesn't exist");
        }
        return null; //TODO It has to be changed later, guess so
    }

    public UserEnt getUserByUsername(String username) {
        try {
            for (UserEnt user : userList) {
                if (user.getUserName().equals(username)) {
                    return user;
                }
            }

        } catch (Exception e) {
            System.out.println("User with given username doesn't exist");
        }
        return null;
    }

    @PostConstruct
    private void MockUserDb() {
        addUser(new ManagerEnt("Iks", "Iksiński", "ROOTer","12345"));
        addUser(new ClientEnt("Igrek", "Igrekowski", "Klientela","12345"));
        addUser(new LibraryOwnerEnt("Dobry", "Ziomek", "ikriss","12345"));
    }
}
