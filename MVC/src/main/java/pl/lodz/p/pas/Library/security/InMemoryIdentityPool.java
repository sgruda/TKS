package pl.lodz.p.pas.Library.security;

import pl.lodz.p.pas.Library.model.Client;
import pl.lodz.p.pas.Library.model.LibraryOwner;
import pl.lodz.p.pas.Library.model.Manager;
import pl.lodz.p.pas.Library.model.User;
import pl.lodz.p.pas.Library.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class InMemoryIdentityPool implements IdentityStore {


    @Inject
    UserService userService;


    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        User user = userService.getAccount(login.getCaller());
        if (user !=null && login.getPasswordAsString().equals(user.getPassword()) && user instanceof LibraryOwner && user.isActive()) {
            return new CredentialValidationResult(login.getCaller(), new HashSet<>(Arrays.asList("ADMIN")));
        } else if (user !=null && login.getPasswordAsString().equals(user.getPassword()) && user instanceof Manager && user.isActive()) {
            return new CredentialValidationResult(login.getCaller(), new HashSet<>(Arrays.asList("MANAGER")));
         }else if(user !=null && login.getPasswordAsString().equals(user.getPassword()) && user instanceof Client && user.isActive()){
            return new CredentialValidationResult(login.getCaller(), new HashSet<>(Arrays.asList("CLIENT")));
        }
        else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
