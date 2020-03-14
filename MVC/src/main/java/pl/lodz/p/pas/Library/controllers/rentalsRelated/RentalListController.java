package pl.lodz.p.pas.Library.controllers.rentalsRelated;

import pl.lodz.p.pas.Library.model.*;
import pl.lodz.p.pas.Library.services.ResourceService;
import pl.lodz.p.pas.Library.services.RentalService;
import pl.lodz.p.pas.Library.services.UserService;

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

    private List<Rental> rentalList;

    private List<Resource> resourceList;

    private List<Client> clientList;

    private Resource resource;

    private Client client;

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


    public List<Rental> getRentalList() {
        return rentalList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Client getClientById(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("No id provided");
        }
        for (Client client : clientList) {
            if (uuid.equals(client.getUUID())) {
                return client;
            }
        }
        return null;
    }

    public Resource getProductById(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("No id provided");
        }
        for (Resource resource : resourceList) {
            if (uuid.equals(resource.getUUID())) {
                return resource;
            }
        }
        return null;
    }

    public boolean checkIfRentable(){
        for (Rental rental: rentalList) {
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

    public void removeSelectedRental(Rental rental) {
        rentalService.removeRental(rental);
        loadData();
    }



    public List<Client> getActiveClients() {
        List<Client> clients = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            if ((user.getClass().equals(Client.class) && (user.isActive()))){
                clients.add((Client) user);
            }
        }
        return clients;
    }

    public List<Client> filterClients() {
        List<Client> clients = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            if (user.getClass().equals(Client.class)) {
                clients.add((Client) user);
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
