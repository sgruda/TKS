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
import java.util.logging.Level;
import java.util.logging.Logger;

@SessionScoped
@Named
public class AddRentalController implements Serializable {
    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Client getClient() {
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

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

    private Date endDate;

    private boolean dateIncorrect = false;

    private boolean rented = false;

    public void createNewRental() throws Exception {
        rentalList = rentalService.getAllRentals();
        checkIfRentable();
        System.out.println(client.getUserName() + ", " + resource.getTitle() + ", " + startDate);
        dateIncorrect = false;
        if(endDate.after(startDate)){
            if(!rented) {
                rentalService.addRental(new Rental(client, resource, startDate, endDate));
                loadRentals();
                client = null;
                resource = null;
                startDate = null;
                endDate = null;
                rented = false;
                dateIncorrect = false;
            }else{
                rented = true;
            }
        }
        else{
            dateIncorrect = true;
        }

    }
    public boolean checkIfRentable(){
        for (Rental rental: rentalList) {
            if(rental.getResource().equals(resource)){
                if(startDate.after(rental.getEndDate()) && endDate.after(rental.getEndDate())){
                    rented = false;
                    return true;
                }
                else{
                    rented = true;
                    return false;
                }
            }
        }
        rented = false;
        return true;
    }

    public void loadRentals() {
        rentalList = rentalService.getAllRentals();
    }

    public List<Client> getActiveClient() {

        List<Client> clients = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            if ((user.getClass().equals(Client.class) && (user.isActive()) && (user.getUserName().equals(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName())))){
                clients.add((Client) user);
            }
        }
        return clients;
    }

    @PostConstruct
    public void loadData() {
        rentalList = rentalService.getAllRentals();
        clientList = getActiveClient();
        resourceList = resourceService.getAllResources();
    }


}
