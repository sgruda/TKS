package pl.lodz.p.pas.Library.services;

import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Resource;
import pl.lodz.p.pas.Library.model.Rental;
import pl.lodz.p.pas.Library.repositories.ResourceRepository;
import pl.lodz.p.pas.Library.repositories.RentalRepository;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class ResourceService implements Serializable {
    @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private RentalRepository rentalRepository;

    public List<Resource> getAllResources() {
        return resourceRepository.getAllResources();
    }

    public void addResource(Resource resource) {
        resourceRepository.addResource(resource);
    }

    public void removeResource(Resource resource) {
        for (Rental rental : rentalRepository.getAllRentals()) {
            if (rental.getResource().equals(resource)) {
                rental.setResource(null);
            }
        }
        resourceRepository.removeResource(resource);
    }

    public void removeResourceBasedOnTitle(String title) {
        resourceRepository.removeResource(resourceRepository.getResourceByTitle(title));
    }

    public Resource getResource(Resource resource) {
      return  resourceRepository.getResource(resource);
    }

    public Resource getResourceByTitle(String title) {
        return resourceRepository.getResourceByTitle(title);
    }

    public void updateResource(Resource resource) {
        Resource temp;
        for (Resource res: resourceRepository.getAllResources()) {
                if(res.getUUID().equals(resource.getUUID())){
                    temp = res;
                    resourceRepository.removeResource(temp);
                }
        }

        resourceRepository.addResource(resource);

    }

    public List<Resource> getResourceContainsInTitle(String str) {
        return resourceRepository.getResourceContainsInTitle(str);
    }
}
