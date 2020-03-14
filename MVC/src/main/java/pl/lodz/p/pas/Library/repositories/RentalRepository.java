package pl.lodz.p.pas.Library.repositories;

import pl.lodz.p.pas.Library.model.Rental;
import pl.lodz.p.pas.Library.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RentalRepository {
    private List<Rental> rentalList = new ArrayList<>();

    public synchronized List<Rental> getAllRentals() {
        return new ArrayList<>(rentalList);
    }

    public synchronized void addRental(Rental rental) {
        this.rentalList.add(rental);
    }

    public void removeRental(Rental rental) {
        try {
            this.rentalList.remove(rental);
        } catch (Exception e) {
            System.out.println("Rental doesn't exist");
        }
    }

    public synchronized Rental getRental(Rental rental) {
        try {
            if (rentalList.contains(rental)) {
                return rental;
            }
        } catch (Exception e) {
            System.out.println("Rental doesn't exist");
        }
        return null;
    }

    public List<Rental> getRentalsByClient(User user) {
        List<Rental> rentals = new ArrayList<>();
        try {
            for (Rental rental : rentalList) {
                if (rental.getClient().equals(user)) {
                   rentals.add(rental);
                }
                return rentals;
            }

        } catch (Exception e) {
            System.out.println("Rentals with given client doesn't exist");
        }
        return null;
    }
}
