package objects;

import java.math.BigDecimal;
import java.sql.Date;

public class Reservation {

    /*
    CODE int(11) PRIMARY KEY,
    Room char(5) NOT NULL,
    CheckIn date NOT NULL,
    Checkout date NOT NULL,
    Rate DECIMAL(6,2) NOT NULL,
    LastName varchar(15) NOT NULL,
    FirstName varchar(15) NOT NULL,
    Adults int(11) NOT NULL,
    Kids int(11) NOT NULL,
    FOREIGN KEY (Room) REFERENCES lab7_rooms (RoomCode)
     */

    int CODE;
    String Room;
    Date CheckIn;
    Date Checkout;
    BigDecimal Rate;
    String LastName;
    String FirstName;
    int Adults;
    int Kids;

    public Reservation(int newCode, String newRoom, Date newCheckIn, Date newCheckout, BigDecimal newRate,
                       String newLastName, String newFirstName, int newAdults, int newKids) {
        this.CODE = newCode;
        this.Room = newRoom;
        this.CheckIn = newCheckIn;
        this.Checkout = newCheckout;
        this.Rate = newRate;
        this.LastName = newLastName;
        this.FirstName = newFirstName;
        this.Adults = newAdults;
        this.Kids = newKids;
    }


    public String toString() {
        return CODE + ", " + Room + ", " + CheckIn + ", " + Checkout + ", " +
                Rate + ", " + LastName + ", " + FirstName + ", " + Adults + ", " + Kids;
    }


}
