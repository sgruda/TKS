package pl.lodz.p.pas.Library.controllers.resourcesRelated;
import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.model.Resource;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ConversationScoped
@Named
public class DetailsResourceController implements Serializable {
    @Inject
    private Conversation conversation;
    private Resource resource;

    public void start() {
        conversation.begin();
    }

    public void end() { conversation.end(); }

    public String onClick(Resource resource) {
        start();
        this.resource = resource;
        return "/manager/detailsResource.xhtml?faces-redirect=true";
    }

    public String onFinish() {
        end();
        return "resourceList.xhtml?faces-redirect=true";
    }

    public Resource getResource() {
        return resource;
    }

    public boolean isBook() {
        return this.resource.getClass() == Book.class;
    }
    public boolean isNewspaper() {
        return this.resource.getClass() == Newspaper.class;
    }
    
}
