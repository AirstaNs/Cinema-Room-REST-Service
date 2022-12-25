package cinema.service;

import cinema.exception.SeatOutOfBoundsException;
import cinema.model.Seat;
import cinema.exception.SeatOccuredException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    public static final int totalRows = 9;
    public static final int totalColumns = 9;

    @JsonIgnore
    public  final List<Seat> seats;

    public RoomService() {
        this.seats = init();
    }
    private List<Seat>  init(){
        List<Seat> lists = new ArrayList<>();
        int start = 1;
        for (int row = start; row <=totalRows ; row++) {
            for (int column = start; column <=totalColumns ; column++) {
                lists.add(new Seat(row,column));
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

    public List<Seat> getAvailableSeats(){
        return seats;
    }
    public Seat takeSeat(Seat seat) throws SeatOutOfBoundsException {
        Seat seat2 = seats.stream()
                         .filter(seat1 -> seat1.equals(seat))
                         .findFirst().get();

        return seat2;
    }
    private void takeSeatOrThrows( Seat seat) throws  SeatOccuredException{
        if (!seat.isAvailable()){
            throw  new SeatOccuredException();
        }else {
            seat.setAvailable(false);
        }
    }

}
