package pl.lodz.p.pas.Library.repositories;

import pl.lodz.p.pas.Library.model.*;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public synchronized List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }

    public synchronized void addUser(User user) {
        this.userList.add(user);
    }

    public synchronized User getUser(User user) {
        try {
            if (userList.contains(user)) {
                return user;
            }
        } catch (Exception e) {
            System.out.println("User doesn't exist");
        }
        return null; //TODO It has to be changed later, guess so
    }

    public User getUserByUsername(String username) {
        try {
            for (User user : userList) {
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
        addUser(new Manager("Iks", "Iksiński", "ROOTer","12345"));
        addUser(new Client("Igrek", "Igrekowski", "Klientela","12345"));
        addUser(new LibraryOwner("Dobry", "Ziomek", "ikriss","12345"));
    }
}
