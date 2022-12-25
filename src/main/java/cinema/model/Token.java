package cinema.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.UUID;

public class Token {
    private UUID token;

    public Token(UUID token) {
        this.token = token;
    }
    @JsonCreator
    public Token(String token){
        this.token = UUID.fromString(token);
    }

    @JsonGetter
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token token1)) return false;

        return this.getToken().toString().equals(token1.getToken().toString());
    }

    @Override
    public int hashCode() {
        return getToken().hashCode();
    }
}
