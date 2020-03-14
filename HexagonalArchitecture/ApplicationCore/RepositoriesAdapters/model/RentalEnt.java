package model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RentalEnt {
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



