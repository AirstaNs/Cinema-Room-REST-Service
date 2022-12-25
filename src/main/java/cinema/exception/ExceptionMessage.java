package cinema.exception;

public class ExceptionMessage {
    private  String error;

    public ExceptionMessage(String error) {
        this.error =error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
