package pl.lodz.p.pas.Library.repositories;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RentalRepository {
    private List<RentalEnt> rentalList = new ArrayList<>();

    public synchronized List<RentalEnt> getAllRentals() {
        return new ArrayList<>(rentalList);
    }

    public synchronized void addRental(RentalEnt rental) {
        this.rentalList.add(rental);
    }

    public void removeRental(RentalEnt rental) {
        try {
            this.rentalList.remove(rental);
        } catch (Exception e) {
            System.out.println("Rental doesn't exist");
        }
    }

    public synchronized RentalEnt getRental(RentalEnt rental) {
        try {
            if (rentalList.contains(rental)) {
                return rental;
            }
        } catch (Exception e) {
            System.out.println("Rental doesn't exist");
        }
        return null;
    }

    public List<RentalEnt> getRentalsByClient(UserEnt user) {
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
