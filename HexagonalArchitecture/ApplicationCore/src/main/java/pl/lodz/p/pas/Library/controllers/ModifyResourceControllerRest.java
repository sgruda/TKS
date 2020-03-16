package pl.lodz.p.pas.Library.controllers;

import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.model.Resource;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@ConversationScoped
@Named
public class ModifyResourceControllerRest implements Serializable {
    @Inject
    private Conversation conversation;
    private Resource resource;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("http://localhost:8080/DGK/resources/api");

    public String converter(String resourceName){
        String temp= "";
        if(resourceName.contains(" ")){
            temp =  resourceName.replace(" ","-");
        }else{
            temp = resourceName;
        }
        return temp;
    }

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
        if (resource instanceof Book) {
            base.path("modify_book").request(MediaType.APPLICATION_JSON).post(Entity.json(resource), Book.class);
        }
        if(resource instanceof Newspaper) {
            base.path("modify_newspaper").request(MediaType.APPLICATION_JSON).post(Entity.json(resource), Newspaper.class);
        }
        end();
        return "resourceList.xhtml?faces-redirect=true";
    }

    public Resource getResource() {
        return resource;
    }
}
