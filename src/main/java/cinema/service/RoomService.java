package cinema.service;

import cinema.exception.EmptyTicketsException;
import cinema.exception.SeatNotFoundException;
import cinema.exception.SeatOutOfBoundsException;
import cinema.model.Seat;
import cinema.exception.SeatOccuredException;
import cinema.model.Ticket;
import cinema.model.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {
    public static final int totalRows = 9;
    public static final int totalColumns = 9;

    @JsonIgnore
    public final List<Seat> seats;
    @JsonIgnore
    private final Set<Ticket> tickets;
    @JsonIgnore
    private final Statistics statistics;

    public RoomService() {
        this.seats = fillSeat();
        tickets = new HashSet<>();
        this.statistics = new Statistics();
    }

    private List<Seat> fillSeat() {
        List<Seat> lists = new ArrayList<>();
        int start = 1;
        for (int row = start; row <= totalRows; row++) {
            for (int column = start; column <= totalColumns; column++) {
                lists.add(new Seat(row, column));
            }
        }
        return lists;
    }

    public Ticket buyTicket(Seat seat) throws SeatOutOfBoundsException, SeatNotFoundException, EmptyTicketsException {
        this.checkTicketsNull();
        Seat getSeat = takeSeatOrThrows(seat);
        Ticket ticket = new Ticket(new Token(UUID.randomUUID()), getSeat);
        System.out.println(ticket.getToken().getToken().toString());
        tickets.add(ticket);

        statistics.addIncome(ticket.getSeat().getPrice());
        return ticket;
    }

    public Map<String, Seat> returnTicket(Token token) {
        this.checkTicketsNull();
        Ticket removedTicket = tickets.stream()
                                      .filter(ticket -> ticket.getToken().equals(token))
                                      .findFirst()
                                      .orElseThrow(EmptyTicketsException::new);
        tickets.remove(removedTicket);
        statistics.decreaseIncome(removedTicket.getSeat().getPrice());
        return Map.of("returned_ticket", removedTicket.getSeat());
    }

    public Statistics getStatistics() {
        return statistics;
    }

    private void checkTicketsNull() throws EmptyTicketsException {
        if (tickets == null) throw new EmptyTicketsException();
    }

    private Seat takeSeatOrThrows(Seat seat) throws SeatOccuredException, SeatNotFoundException {
        Seat takeSeat = seats.stream()
                             .filter(seatRoom -> seatRoom.equals(seat))
                             .findFirst()
                             .orElseThrow(SeatNotFoundException::new);

        if (!takeSeat.isAvailable()) {
            throw new SeatOccuredException();
        } else {takeSeat.setAvailable(false);}

        return takeSeat;
    }


    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return seats;
    }


    public class Statistics {

        private int income = 0;

        @JsonProperty("current_income")
        public int getIncome() {
            return income;
        }

        @JsonProperty("number_of_available_seats")
        public int getAvailableSeats() {
            return seats.size() - tickets.size();
        }

        @JsonProperty("number_of_purchased_tickets")
        public int getPurchasedTickets() {
            return tickets.size();
        }

        private void addIncome(int income) {
            this.income += income;
        }

        private void decreaseIncome(int income) {
            this.income -= income;
        }
    }
}
