import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

class Passenger {
    String passportNumber;
    String fullName;
    Date birthDate;

    public Passenger(String passportNumber, String fullName, Date birthDate) {
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }
}

class FlightTicket {
    String flightNumber;
    Date departureDate;
    Passenger passenger;
    boolean isReserved;
    boolean isCancelled;

    public FlightTicket(String flightNumber, Date departureDate, Passenger passenger) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.passenger = passenger;
        this.isReserved = false;
        this.isCancelled = false;
    }

    public void reserve() {
        if (!isCancelled) {
            isReserved = true;
            System.out.println("Ticket reserved for flight " + flightNumber);
        } else {
            System.out.println("Ticket is cancelled and cannot be reserved.");
        }
    }

    public void cancel() {
        if (isReserved) {
            isReserved = false;
            isCancelled = true;
            System.out.println("Reservation cancelled for flight " + flightNumber);
        } else {
            System.out.println("Ticket is not reserved, so cannot be cancelled.");
        }
    }
}

public class FlightTicketSystem {
    List<FlightTicket> tickets = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public void bookTicket(String passportNumber, String fullName, String birthDateString, String flightNumber, String departureDateString) throws Exception {
        Date birthDate = formatter.parse(birthDateString);
        Date departureDate = formatter.parse(departureDateString);
        Passenger passenger = new Passenger(passportNumber, fullName, birthDate);
        FlightTicket ticket = new FlightTicket(flightNumber, departureDate, passenger);
        tickets.add(ticket);
        System.out.println("Ticket booked for " + fullName);
    }

    public static void main(String[] args) {
        // Bu alanı kullanıcı etkileşimi için kullanabilirsiniz.
        // Örneğin, kullanıcıdan bilet detayları alıp bu detaylara göre bir bilet rezerve edebilirsiniz.
        try {
            FlightTicketSystem system = new FlightTicketSystem();
            // Örnek kullanım:
            system.bookTicket("U1233456", "Ozgur DOK", "01-01-1990", "ABC123", "25-12-2024");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
