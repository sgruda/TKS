package pl.lodz.p.pas.Library.services;


import pl.lodz.p.pas.Library.repositories.RentalRepository;
import pl.lodz.p.tks.model.ClientEnt;
import pl.lodz.p.tks.model.RentalEnt;
import pl.lodz.p.tks.model.UserEnt;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class RentalService implements Serializable {
    @Inject
    private RentalRepository rentalRepo;

    public List<RentalEnt> getAllRentals() {
        return rentalRepo.getAllRentals();
    }

    public List<RentalEnt> getUserRentals(String username){
        ArrayList<RentalEnt> temp = new ArrayList<>();
        for (RentalEnt rental: rentalRepo.getAllRentals()) {
            if(rental.getClient().getUserName().equals(username)){
                temp.add(rental);
            }
        }
        return temp;
    }

    public void addRental(RentalEnt rental) {
        rentalRepo.addRental(rental);
    }

    public void removeRental(RentalEnt rental) {
        rentalRepo.removeRental(rental);
    }

    public void removeRentalsBasedOnClient(ClientEnt client) {
        for (RentalEnt rental: rentalRepo.getRentalsByClient(client)) {
            rentalRepo.removeRental(rental);
        }
    }

    public List<RentalEnt> getRentalsByClient(UserEnt user) {
        return rentalRepo.getRentalsByClient(user);
    }

    public void getRental(RentalEnt rental) {
        rentalRepo.getRental(rental);
    }
}
