import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println(airport.getAllAircrafts().size());
        List<Terminal> terminals = airport.getTerminals();
        List<Flight> flights = new ArrayList<>();
        Date now = new Date();

        terminals.forEach(terminal -> flights.addAll(terminal.getFlights()));

        flights.stream()
                .filter(flight -> flight.getDate().after(now)
                        && flight.getDate().before(new Date(now.getTime() +1000*60*60*2))
                        && flight.getType().equals(Flight.Type.DEPARTURE))
                .forEach(flight -> System.out.println(flight.getDate() + " " + flight.getAircraft()));
    }
}
