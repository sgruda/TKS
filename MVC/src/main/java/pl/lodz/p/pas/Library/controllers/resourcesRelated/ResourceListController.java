package pl.lodz.p.pas.Library.controllers.resourcesRelated;

import pl.lodz.p.pas.Library.model.*;
import pl.lodz.p.pas.Library.services.ResourceService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@Named
public class ResourceListController implements Serializable {
    @Inject
    private ResourceService resourceService;

    private List<Resource> resourceList;

    private String title;

    private Category category;

    private Integer releaseYear;

    private String author;

    private Integer issueNumber;

    private String productType;
    private String filterStr;

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getfilterStr() {
        return filterStr;
    }

    public void setfilterStr(String filterStr) {
        this.filterStr = filterStr;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public boolean isBook(Resource resource) {
        return resource.getClass() == Book.class;
    }

    public boolean isNewspaper(Resource resource) {
        return resource.getClass() == Newspaper.class;
    }


    public void createNewProduct() {
        System.out.println(productType + ", " + title + ", " + category);
        if (productType.equals("Book")) {
            resourceService.addResource(new Book(title, category, releaseYear, author));
            loadProducts();
        }
        if (productType.equals("Newspaper")) {
            resourceService.addResource(new Newspaper(title, category, issueNumber));
            loadProducts();
        }

        title = "";
        category = null;
        releaseYear = null;
        author = "";
        issueNumber = null;
    }

    public void removeSelectedProduct(Resource resource) {
        resourceService.removeResource(resource);
        loadProducts();
    }

    public void removeSelectedProductBasedOnTitle(String title) {
        resourceService.removeResourceBasedOnTitle(title);
        loadProducts();
    }

    public void getProductsContainsInTitle() {
        resourceList = resourceService.getResourceContainsInTitle(filterStr);
    }

    @PostConstruct
    public void loadProducts() {
        resourceList = resourceService.getAllResources();
    }
}
