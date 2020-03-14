package pl.lodz.p.pas.Library.repositories;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResourceRepository {
    private List<ResourceEnt> resourceList = new ArrayList<>();

    public List<ResourceEnt> getAllResources() {
        return new ArrayList<>(resourceList);
    }

    public synchronized void addResource(ResourceEnt resource) {
        this.resourceList.add(resource);
    }

    public synchronized void removeResource(ResourceEnt resource) {
        try {
            this.resourceList.remove(resource);
        } catch (Exception e) {
            System.out.println("Product doesn't exist");
        }
    }

    public synchronized ResourceEnt getResource(ResourceEnt resource) {
        try {
            if (resourceList.contains(resource)) {
                return resource;
            }
        } catch (Exception e) {
            System.out.println("Resource doesn't exist");
        }
        return null;
    }

    public synchronized ResourceEnt getResourceByTitle(String title) {
        try {
            for (ResourceEnt resource : resourceList) {
                if (resource.getTitle().equals(title)) {
                    return resource;
                }
            }

        } catch (Exception e) {
            System.out.println("Resource with given title doesn't exist");
        }
        return null;
    }
    public List<ResourceEnt> getResourceContainsInTitle(String str) {
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
        addResource(new BookEnt("Lord Of The Rings", Category.Fantasy, 1954, "J.R.R. Tolkien"));
        addResource(new BookEnt("The Brothers Karamazov", Category.Philosophical, 1879, "Fyodor Dostoevsky"));
        addResource(new NewspaperEnt("CKM", Category.Lifestyle, 4));
    }
}
