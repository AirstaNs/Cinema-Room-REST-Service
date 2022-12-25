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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    public static final int totalRows = 9;
    public static final int totalColumns = 9;

    @JsonIgnore
    public final List<Seat> seats;
    @JsonIgnore
    private final List<Ticket> tickets;

    public RoomService() {
        this.seats = init();
        tickets = new ArrayList<>();
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

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return seats;
    }

    public Ticket buyTicket(Seat seat) throws SeatOutOfBoundsException,SeatNotFoundException,EmptyTicketsException {
        this.checkTicketsEmpty();
      Ticket ticket =  new Ticket(new Token(UUID.randomUUID()),seat);
      tickets.add(ticket);

//        Seat takeSeat = seats.stream()
//                             .filter(seatRoom -> seatRoom.equals(seat))
//                             .findFirst()
//                             .orElseThrow(SeatNotFoundException::new);
//
//        this.takeSeatOrThrows(takeSeat);
        return ticket;
    }
    private void checkTicketsEmpty() throws EmptyTicketsException{
        if(tickets==null|| tickets.isEmpty()) throw new EmptyTicketsException();
    }
    private void takeSeatOrThrows(Seat seat) throws SeatOccuredException {
        if (!seat.isAvailable()) {
            throw new SeatOccuredException();
        } else {
            seat.setAvailable(false);
        }
    }
}
