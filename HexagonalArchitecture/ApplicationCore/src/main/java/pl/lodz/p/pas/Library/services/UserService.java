package pl.lodz.p.pas.Library.services;

import pl.lodz.p.pas.Library.repositories.RentalRepository;
import pl.lodz.p.pas.Library.repositories.UserRepository;
import pl.lodz.p.tks.model.UserEnt;

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

    public List<UserEnt> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public void addUser(UserEnt user) {
        userRepo.addUser(user);
    }

    public void activateUser(UserEnt user){
        userRepo.getUser(user).setActive(true);
    }

    public void deactivateUser(UserEnt user){
        userRepo.getUser(user).setActive(false);
    }

    public UserEnt getAccount(String username) {
        for (UserEnt user: userRepo.getAllUsers()) {
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }

    public boolean checkIfAlreadyExists(String username){
        for (UserEnt user :userRepo.getAllUsers()) {
            if(user.getUserName().equals(username)){
                return  true;
            }
        }
        return false;
    }
}
