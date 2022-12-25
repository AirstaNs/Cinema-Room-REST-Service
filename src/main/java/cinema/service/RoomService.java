package cinema.service;

import cinema.exception.EmptyTicketsException;
import cinema.exception.SeatNotFoundException;
import cinema.exception.SeatOutOfBoundsException;
import cinema.model.Seat;
import cinema.exception.SeatOccuredException;
import cinema.model.Ticket;
import cinema.model.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public RoomService() {
        this.seats = init();
        tickets = new HashSet<>();
    }

    private List<Seat> init() {
        List<Seat> lists = new ArrayList<>();
        int start = 1;
        for (int row = start; row <= totalRows; row++) {
            for (int column = start; column <= totalColumns; column++) {
                lists.add(new Seat(row, column));
            }
        }
        return lists;
    }

    public Ticket buyTicket(Seat seat) throws SeatOutOfBoundsException,SeatNotFoundException,EmptyTicketsException {
        this.checkTicketsNull();
      Ticket ticket =  new Ticket(new Token(UUID.randomUUID()),seat);
        System.out.println(ticket.getToken().getToken().toString());
        tickets.add(ticket);

//        Seat takeSeat = seats.stream()
//                             .filter(seatRoom -> seatRoom.equals(seat))
//                             .findFirst()
//                             .orElseThrow(SeatNotFoundException::new);
//
//        this.takeSeatOrThrows(takeSeat);
        return ticket;
    }

    public Map<String, Seat> returnTicket(Token token) {
        this.checkTicketsNull();
        Ticket removedTicket = tickets.stream()
                           .filter(ticket -> ticket.getToken().equals(token))
                           .findFirst()
                           .orElseThrow(EmptyTicketsException::new);
        tickets.remove(removedTicket);
        return Map.of("returned_ticket",removedTicket.getSeat());
    }

    private void checkTicketsNull() throws EmptyTicketsException{
        if(tickets==null) throw new EmptyTicketsException();
    }
    private void takeSeatOrThrows(Seat seat) throws SeatOccuredException {
        if (!seat.isAvailable()) {
            throw new SeatOccuredException();
        } else {
            seat.setAvailable(false);
        }
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

}
