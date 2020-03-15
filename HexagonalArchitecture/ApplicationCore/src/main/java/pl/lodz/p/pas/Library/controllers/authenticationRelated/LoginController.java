package pl.lodz.p.pas.Library.controllers.authenticationRelated;

import pl.lodz.p.pas.Library.controllers.usersRelated.UserListController;
import pl.lodz.p.pas.Library.services.UserService;
import pl.lodz.p.tks.model.ClientEnt;
import pl.lodz.p.tks.model.LibraryOwnerEnt;
import pl.lodz.p.tks.model.ManagerEnt;
import pl.lodz.p.tks.model.UserEnt;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Named
public class LoginController implements Serializable {

    @Inject
    private UserListController userListController;

    @Inject
    private UserService userService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userType;

    @NotEmpty
    @Size(min = 5, message = "Username must have at least 5 characters")
    private String username;

    @NotEmpty
    @Size(min = 5, message = "Password must have at least 5 characters")
    private String password;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;


    public void submit() throws IOException {

        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, "Custom logger message : Login failed");
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));

                break;
            case SUCCESS:
                Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "Custom logger message : Login succeeded");
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
                UserEnt user = userService.getAccount(username);
                if(user instanceof LibraryOwnerEnt){
                    userType = "admin";
                }else if(user instanceof ManagerEnt){
                    userType = "manager";
                } else if (user instanceof ClientEnt){
                    userType = "client";
                }
                userListController.setUserType(userType);
                switch (userType) {
                    case ("admin"):
                        externalContext.redirect(externalContext.getRequestContextPath() + "/admin/adminHomePage.xhtml?faces-redirect=true");
                        break;
                    case "client":
                        externalContext.redirect(externalContext.getRequestContextPath() + "/client/clientHomePage.xhtml");
                        break;
                    case "manager":
                        externalContext.redirect(externalContext.getRequestContextPath() + "/manager/managerHomePage.xhtml");
                        break;
                }
                break;
            case NOT_DONE:
        }
    }

    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password))
        );
    }
}
