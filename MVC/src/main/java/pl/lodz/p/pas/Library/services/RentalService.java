package pl.lodz.p.pas.Library.services;

import pl.lodz.p.pas.Library.model.Client;
import pl.lodz.p.pas.Library.model.Rental;
import pl.lodz.p.pas.Library.model.User;
import pl.lodz.p.pas.Library.repositories.RentalRepository;

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

    public List<Rental> getAllRentals() {
        return rentalRepo.getAllRentals();
    }

    public List<Rental> getUserRentals(String username){
        ArrayList<Rental> temp = new ArrayList<>();
        for (Rental rental: rentalRepo.getAllRentals()) {
            if(rental.getClient().getUserName().equals(username)){
                temp.add(rental);
            }
        }
        return temp;
    }

    public void addRental(Rental rental) {
        rentalRepo.addRental(rental);
    }

    public void removeRental(Rental rental) {
        rentalRepo.removeRental(rental);
    }

    public void removeRentalsBasedOnClient(Client client) {
        for (Rental rental: rentalRepo.getRentalsByClient(client)) {
            rentalRepo.removeRental(rental);
        }
    }

    public List<Rental> getRentalsByClient(User user) {
        return rentalRepo.getRentalsByClient(user);
    }

    public void getRental(Rental rental) {
        rentalRepo.getRental(rental);
    }
}
