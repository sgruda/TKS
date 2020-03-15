package pl.lodz.p.pas.Library.controllers.authenticationRelated;

import com.captcha.botdetect.web.jsf.JsfCaptcha;
import pl.lodz.p.pas.Library.model.Client;
import pl.lodz.p.pas.Library.model.PersonalData;
import pl.lodz.p.pas.Library.model.User;
import pl.lodz.p.pas.Library.services.UserService;
import pl.lodz.p.tks.model.ClientEnt;
import pl.lodz.p.tks.model.PersonalDataEnt;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ApplicationScoped
@Named
public class RegisterController implements Serializable {

    @Inject
    private UserService userService;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    private String userName;

    private String password;


    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public JsfCaptcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(JsfCaptcha captcha) {
        this.captcha = captcha;
    }

    private String captchaCode;

    private JsfCaptcha captcha;

    private String confirmPassword;

    private String firstName;

    private String lastName;

    public boolean isDoesExists() {
        return doesExists;
    }

    public void setDoesExists(boolean doesExists) {
        this.doesExists = doesExists;
    }

    private boolean doesExists;

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }

    public boolean isPasswordDoesMatch() {
        return passwordDoesMatch;
    }

    public void setPasswordDoesMatch(boolean passwordDoesMatch) {
        this.passwordDoesMatch = passwordDoesMatch;
    }

    private boolean userCreated = false;

    private boolean passwordDoesMatch = false;

    public void checkIfAlreadyExists(){
        if(userService.checkIfAlreadyExists(userName)){
            doesExists = true;
        }else{
            doesExists = false;
        }
    }

    public void checkIfPasswordsMatch(){
        if(password.matches(confirmPassword)){
            passwordDoesMatch = true;
        }else{
            passwordDoesMatch = false;
        }
    }

    public void createNewClient(){
        boolean isHuman = captcha.validate(captchaCode);
        checkIfPasswordsMatch();
        checkIfAlreadyExists();
        if(isHuman){
        if((passwordDoesMatch) && (!doesExists)){
            userService.addUser(new ClientEnt(new PersonalDataEnt(firstName,lastName,userName,password)));
            System.out.println("User added");
            userCreated = true;
            userName = null;
            firstName = null;
            lastName = null;
            this.captchaCode = "";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Signup Success", "Thank you for signing up!");
        }else{
            userCreated = false;
        }
        }


    }



}
