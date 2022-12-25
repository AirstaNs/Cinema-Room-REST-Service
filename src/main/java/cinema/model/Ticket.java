package cinema.model;

import com.fasterxml.jackson.annotation.*;

public class Ticket {
    @JsonUnwrapped
    private Token token;
    @JsonProperty(value = "ticket")
    private Seat seat;

    public Ticket(Token token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
