package cinema.model;

import cinema.service.RoomService;
import cinema.exception.SeatOutOfBoundsException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Seat {
    private int row;
    private int column;
    @JsonIgnore
    private boolean isAvailable;

    private int price = -1;

    @JsonCreator
    public Seat(@JsonProperty("row") Integer row, @JsonProperty("column") Integer column) {
        this.setRow(row);
        this.setColumn(column);
        this.setAvailable(true);
        this.setPrice();
    }

    private void setPrice() throws SeatOutOfBoundsException {
        int price_ten = 10, price_eight = 8, row_condition = 4;
        this.checkValue(row);
        this.price = (this.row <= row_condition) ? price_ten : price_eight;
    }

    private void checkValue(int value) throws SeatOutOfBoundsException {
        int minRowOrColumn = 0;
        if (value <= minRowOrColumn || value > RoomService.totalRows) throw new SeatOutOfBoundsException();
    }

    @JsonIgnore
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.checkValue(row);
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public void setColumn(int column) {
        this.checkValue(column);
        this.column = column;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Seat{");
        sb.append("row=").append(row);
        sb.append(", column=").append(column);
        sb.append(", isAvailable=").append(isAvailable);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() != o.hashCode()) return false;
        if (this == o) return true;
        if (!(o instanceof Seat)) return false;

        Seat seat = (Seat) o;

        boolean row = this.getRow() == seat.getRow();
        boolean column = this.getColumn() == seat.getColumn();

        return row & column;
    }

    @Override
    public int hashCode() {
        int result = getRow();
        result = 31 * result + getColumn();
        return result;
    }
}
