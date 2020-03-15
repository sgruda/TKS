package pl.lodz.p.pas.Library.controllers.rentalsRelated;

import pl.lodz.p.pas.Library.model.*;
import pl.lodz.p.pas.Library.services.ResourceService;
import pl.lodz.p.pas.Library.services.RentalService;
import pl.lodz.p.pas.Library.services.UserService;
import pl.lodz.p.tks.model.ClientEnt;
import pl.lodz.p.tks.model.RentalEnt;
import pl.lodz.p.tks.model.ResourceEnt;
import pl.lodz.p.tks.model.UserEnt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named
public class RentalListController implements Serializable {
    @Inject
    private RentalService rentalService;
    @Inject
    private ResourceService resourceService;
    @Inject
    private UserService userService;

    private List<RentalEnt> rentalList;

    private List<ResourceEnt> resourceList;

    private List<ClientEnt> clientList;

    private ResourceEnt resource;

    private ClientEnt client;

    private Date startDate;

    public boolean isDateIncorrect() {
        return dateIncorrect;
    }

    public void setDateIncorrect(boolean dateIncorrect) {
        this.dateIncorrect = dateIncorrect;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    private boolean dateIncorrect = false;

    private boolean rented = false;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date endDate;


    public List<RentalEnt> getRentalList() {
        return rentalList;
    }

    public List<ClientEnt> getClientList() {
        return clientList;
    }

    public List<ResourceEnt> getResourceList() {
        return resourceList;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ClientEnt getClientById(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("No id provided");
        }
        for (ClientEnt client : clientList) {
            if (uuid.equals(client.getUUID())) {
                return client;
            }
        }
        return null;
    }

    public ResourceEnt getProductById(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("No id provided");
        }
        for (ResourceEnt resource : resourceList) {
            if (uuid.equals(resource.getUUID())) {
                return resource;
            }
        }
        return null;
    }

    public boolean checkIfRentable(){
        for (RentalEnt rental: rentalList) {
            if(rental.getResource().equals(resource)){
                if(startDate.after(rental.getEndDate())){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    public void removeSelectedRental(RentalEnt rental) {
        rentalService.removeRental(rental);
        loadData();
    }



    public List<ClientEnt> getActiveClients() {
        List<ClientEnt> clients = new ArrayList<>();
        for (UserEnt user : userService.getAllUsers()) {
            if ((user.getClass().equals(ClientEnt.class) && (user.isActive()))){
                clients.add((ClientEnt) user);
            }
        }
        return clients;
    }

    public List<ClientEnt> filterClients() {
        List<ClientEnt> clients = new ArrayList<>();
        for (UserEnt user : userService.getAllUsers()) {
            if (user.getClass().equals(Client.class)) {
                clients.add((ClientEnt) user);
            }
        }
        return clients;
    }


    public void loadRentals() {
        rentalList = rentalService.getAllRentals();
    }

    @PostConstruct
    public void loadData() {
        rentalList = rentalService.getAllRentals();
        clientList = getActiveClients();
        resourceList = resourceService.getAllResources();
    }
}
