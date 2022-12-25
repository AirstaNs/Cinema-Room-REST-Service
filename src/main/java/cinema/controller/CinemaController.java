package cinema.controller;

import cinema.model.Ticket;
import cinema.model.Token;
import cinema.service.RoomService;
import cinema.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ComponentScan({"cinema.model", "cinema.service"})
public class CinemaController {
    private final RoomService roomService;

    @Autowired
    public CinemaController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/seats")
    public RoomService getSeats() {
        return roomService;
    }

    @PostMapping(path = "/purchase", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Ticket> buySeats(@RequestBody Seat seat) {
        return new ResponseEntity<>(roomService.buyTicket(seat), HttpStatus.OK);
    }
    @PostMapping(path = "/return", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String,Seat>> returnTicket(@RequestBody Token token) {
        return new ResponseEntity<>(roomService.returnTicket(token), HttpStatus.OK);
    }
}
