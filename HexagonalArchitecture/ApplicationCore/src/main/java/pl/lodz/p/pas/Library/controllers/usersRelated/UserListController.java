package pl.lodz.p.pas.Library.controllers.usersRelated;

import pl.lodz.p.pas.Library.model.*;
import pl.lodz.p.pas.Library.services.UserService;
import pl.lodz.p.tks.model.UserEnt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@Named
public class UserListController implements Serializable {
    @Inject
    private UserService userService;

    private List<UserEnt> userList;

    private String userName;

    private String firstName;

    private String lastName;

    private String userType;

    public List<UserEnt> getUserList() {
        return userList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void activateUser(UserEnt user) {
        userService.activateUser(user);
    }

    public void deactivateUser(UserEnt user) {
        userService.deactivateUser(user);
    }

    @PostConstruct
    public void loadUsers() {
        userList = userService.getAllUsers();
    }
}
