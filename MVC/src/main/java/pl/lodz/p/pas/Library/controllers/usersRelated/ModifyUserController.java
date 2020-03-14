package pl.lodz.p.pas.Library.controllers.usersRelated;

import pl.lodz.p.pas.Library.model.User;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class ModifyUserController implements Serializable {
    @Inject
    private Conversation conversation;
    private User user;

    public void start() {
        conversation.begin();
    }

    public void end() { conversation.end(); }

    public String onClick(User user) {
        start();
        this.user = user;
        return "/admin/modifyUser.xhtml?faces-redirect=true";
    }

    public String onFinish() {
        end();
        return "userList.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }
}
