package cinema.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.UUID;

public class Token {
    private UUID token;

    public Token(UUID token) {
        this.token = token;
    }

    @JsonGetter
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
