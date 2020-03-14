package pl.lodz.p.pas.Library.controllers.rentalsRelated;

import pl.lodz.p.pas.Library.model.Client;
import pl.lodz.p.pas.Library.model.Rental;
import pl.lodz.p.pas.Library.model.Resource;
import pl.lodz.p.pas.Library.model.User;
import pl.lodz.p.pas.Library.security.InMemoryIdentityPool;
import pl.lodz.p.pas.Library.services.RentalService;
import pl.lodz.p.pas.Library.services.ResourceService;
import pl.lodz.p.pas.Library.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named
public class UserRentalListController implements Serializable {

    @Inject
    private RentalService rentalService;

    private List<Rental> rentalList;

    private Resource resource;

    private Client client;

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public User getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }



    public void removeSelectedRental(Rental rental) {
        rentalService.removeRental(rental);
        loadData();
    }

    public void loadRentals() {
        rentalList = rentalService.getUserRentals(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
    }

    @PostConstruct
    public void loadData() {
        rentalList = rentalService.getUserRentals(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
    }

}
