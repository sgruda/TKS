package pl.lodz.p.pas.Library.services;

import pl.lodz.p.pas.Library.model.User;
import pl.lodz.p.pas.Library.repositories.RentalRepository;
import pl.lodz.p.pas.Library.repositories.UserRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Dependent
public class UserService implements Serializable {

    @Inject
    private UserRepository userRepo;

    @Inject
    private RentalRepository rentalRepo;

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public void addUser(User user) {
        userRepo.addUser(user);
    }

    public void activateUser(User user){
        userRepo.getUser(user).setActive(true);
    }

    public void deactivateUser(User user){
        userRepo.getUser(user).setActive(false);
    }

    public User getAccount(String username) {
        for (User user: userRepo.getAllUsers()) {
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }

    public boolean checkIfAlreadyExists(String username){
        for (User user :userRepo.getAllUsers()) {
            if(user.getUserName().equals(username)){
                return  true;
            }
        }
        return false;
    }
}
