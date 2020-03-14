package pl.lodz.p.pas.Library.repositories;


import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Category;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.model.Resource;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResourceRepository {
    private List<Resource> resourceList = new ArrayList<>();

    public List<Resource> getAllResources() {
        return new ArrayList<>(resourceList);
    }

    public synchronized void addResource(Resource resource) {
        this.resourceList.add(resource);
    }

    public synchronized void removeResource(Resource resource) {
        try {
            this.resourceList.remove(resource);
        } catch (Exception e) {
            System.out.println("Product doesn't exist");
        }
    }

    public synchronized Resource getResource(Resource resource) {
        try {
            if (resourceList.contains(resource)) {
                return resource;
            }
        } catch (Exception e) {
            System.out.println("Resource doesn't exist");
        }
        return null;
    }

    public synchronized Resource getResourceByTitle(String title) {
        try {
            for (Resource resource : resourceList) {
                if (resource.getTitle().equals(title)) {
                    return resource;
                }
            }

        } catch (Exception e) {
            System.out.println("Resource with given title doesn't exist");
        }
        return null;
    }
    public List<Resource> getResourceContainsInTitle(String str) {
        if(str.startsWith(" ") && str.endsWith(" ")) {
            return getAllResources();
        }
        return getAllResources().stream()
                .filter(
                        product -> product.getTitle().toLowerCase().contains(str.toLowerCase())
                )
                .collect(Collectors.toList());
    }
    @PostConstruct
    private void MockProductDb() {
        addResource(new Book("Lord Of The Rings", Category.Fantasy, 1954, "J.R.R. Tolkien"));
        addResource(new Book("The Brothers Karamazov", Category.Philosophical, 1879, "Fyodor Dostoevsky"));
        addResource(new Newspaper("CKM", Category.Lifestyle, 4));
    }
}
