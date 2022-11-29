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

}
