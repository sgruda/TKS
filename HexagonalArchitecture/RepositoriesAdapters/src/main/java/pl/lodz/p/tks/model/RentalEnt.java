package pl.lodz.p.tks.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RentalEnt {
    public ClientEnt getClient() {
        return client;
    }

    public void setClient(ClientEnt client) {
        this.client = client;
    }

    public ResourceEnt getResource() {
        return resource;
    }

    public void setResource(ResourceEnt resource) {
        this.resource = resource;
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

    private ClientEnt client;
    private ResourceEnt resource;
    private Date startDate;
    private Date endDate;

    public RentalEnt(ClientEnt client, ResourceEnt resource, Date startDate) {
        this.client = client;
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = null;
    }

    public RentalEnt(ClientEnt client, ResourceEnt resource, Date startDate, Date endDate) {
        this.client = client;
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}



