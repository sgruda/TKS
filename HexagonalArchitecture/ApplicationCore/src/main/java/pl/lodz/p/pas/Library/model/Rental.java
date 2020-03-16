package pl.lodz.p.pas.Library.model;
import java.util.Date;


public class Rental {
    public void setClient(Client client) {
        this.client = client;
    }

    private Client client;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    private Resource resource;
    private Date startDate;
    private Date endDate;

    public Rental(Client client, Resource resource, Date startDate) {
        this.client = client;
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = null;
    }

    public Rental(Client client, Resource resource, Date startDate, Date endDate) {
        this.client = client;
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public Resource getResource() {
        return resource;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}



