package pl.lodz.p.pas.Library.controllers.resourcesRelated;

import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Category;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.services.ResourceService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Console;
import java.io.Serializable;


@ApplicationScoped
@Named
public class AddResourceController implements Serializable {
    @Inject
    private ResourceService resourceService;

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
            resourceService.addResource(new Book(title, category, releaseYear, author));
        }
        if(resourceType.equals("Newspaper")) {
            resourceService.addResource(new Newspaper(title, category, issueNumber));
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
