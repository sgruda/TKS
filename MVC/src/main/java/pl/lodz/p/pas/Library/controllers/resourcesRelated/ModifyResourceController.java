package pl.lodz.p.pas.Library.controllers.resourcesRelated;

import pl.lodz.p.pas.Library.model.*;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class ModifyResourceController implements Serializable {
    @Inject
    private Conversation conversation;
    private Resource resource;

    public boolean isBook() {
        return resource.getClass() == Book.class;
    }

    public boolean isNewspaper() {
        return resource.getClass() == Newspaper.class;
    }

    public void start() {
        conversation.begin();
    }

    public void end() { conversation.end(); }

    public String onClick(Resource resource) {
        start();
        this.resource = resource;
        return "/manager/modifyResource.xhtml?faces-redirect=true";
    }

    public String onFinish() {
        end();
        return "resourceList.xhtml?faces-redirect=true";
    }

    public Resource getResource() {
        return resource;
    }

}
