package pl.lodz.p.pas.Library.controllers;

import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Category;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.services.ResourceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;


@ApplicationScoped
@Named
public class AddResourceControllerRest implements Serializable {

    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("http://localhost:8080/DGK/resources/api");
    private String resourceType;
    private String title;
    private Category category;
    private Integer releaseYear;        //Book
    private String author;              //Book
    private Integer issueNumber;        //Newspaper

    public String onClick() {
        return "/manager/addResource.xhtml?faces-redirect=true";
    }
    public String onFinish() {
        if (resourceType.equals("Book")) {
            base.path("add_book").request(MediaType.APPLICATION_JSON).post(Entity.json(new Book(title, category, releaseYear, author)), Book.class);
        }
        if(resourceType.equals("Newspaper")) {
            base.path("add_newspaper").request(MediaType.APPLICATION_JSON).post(Entity.json(new Newspaper(title, category, issueNumber)), Newspaper.class);
        }
        clear();
        return "";
    }
    private void clear() {
        title = "";
        category = null;
        releaseYear = null;
        author = "";
        issueNumber = null;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }
}
