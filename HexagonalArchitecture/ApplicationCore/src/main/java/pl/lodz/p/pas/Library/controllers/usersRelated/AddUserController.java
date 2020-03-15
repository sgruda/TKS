package pl.lodz.p.pas.Library.controllers.usersRelated;

import pl.lodz.p.pas.Library.model.Client;
import pl.lodz.p.pas.Library.model.LibraryOwner;
import pl.lodz.p.pas.Library.model.Manager;
import pl.lodz.p.pas.Library.model.PersonalData;
import pl.lodz.p.pas.Library.services.UserService;
import pl.lodz.p.tks.model.ClientEnt;
import pl.lodz.p.tks.model.LibraryOwnerEnt;
import pl.lodz.p.tks.model.ManagerEnt;
import pl.lodz.p.tks.model.PersonalDataEnt;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class AddUserController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private Conversation conversation;
    private String userName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private String firstName;
    private String lastName;
    private String userType;

    public boolean isDoesExists() {
        return doesExists;
    }

    public void setDoesExists(boolean doesExists) {
        this.doesExists = doesExists;
    }

    private boolean doesExists;

    public void start() {
        conversation.begin();
    }

    public void end() { conversation.end(); }

    public String onClick() {
        return "/admin/addUser.xhtml?faces-redirect=true";
    }

    public String onFinish() {
        checkIfAlreadyExists();
        if(!doesExists){
            start();
        switch (userType) {
            case "Admin":
                userService.addUser(new LibraryOwnerEnt(new PersonalDataEnt(firstName, lastName, userName,password)));
                break;
            case "Worker":
                userService.addUser(new ManagerEnt(new PersonalDataEnt(firstName, lastName, userName,password)));
            case "Reader":
                userService.addUser(new ClientEnt(new PersonalDataEnt(firstName, lastName, userName,password)));
            default:
                System.out.println("Wrong user type!");
                break;

           }
           end();
           clear();
        }
        return "";
    }

    public void clear() {
        userName = "";
        firstName = "";
        lastName = "";
        userType = "Select";
    }

    public void checkIfAlreadyExists(){
        if(userService.checkIfAlreadyExists(userName)){
            doesExists = true;
        }else{
            doesExists = false;
        }
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
}
