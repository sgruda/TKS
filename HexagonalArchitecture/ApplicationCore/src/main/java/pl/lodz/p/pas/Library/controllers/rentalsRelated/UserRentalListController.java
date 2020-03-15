package pl.lodz.p.pas.Library.controllers.rentalsRelated;

import pl.lodz.p.pas.Library.model.Client;
import pl.lodz.p.pas.Library.model.Rental;
import pl.lodz.p.pas.Library.model.Resource;
import pl.lodz.p.pas.Library.model.User;
import pl.lodz.p.pas.Library.security.InMemoryIdentityPool;
import pl.lodz.p.pas.Library.services.RentalService;
import pl.lodz.p.pas.Library.services.ResourceService;
import pl.lodz.p.pas.Library.services.UserService;
import pl.lodz.p.tks.model.ClientEnt;
import pl.lodz.p.tks.model.RentalEnt;
import pl.lodz.p.tks.model.ResourceEnt;
import pl.lodz.p.tks.model.UserEnt;

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

    private List<RentalEnt> rentalList;

    private ResourceEnt resource;

    private ClientEnt client;

    public List<RentalEnt> getRentalList() {
        return rentalList;
    }

    public ResourceEnt getResource() {
        return resource;
    }

    public void setResource(ResourceEnt resource) {
        this.resource = resource;
    }

    public UserEnt getClient() {
        return client;
    }

    public void setClient(ClientEnt client) {
        this.client = client;
    }



    public void removeSelectedRental(RentalEnt rental) {
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
