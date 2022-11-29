package objects;

import java.math.BigDecimal;

public class Room {
    /*
    RoomCode char(5) PRIMARY KEY,
    RoomName varchar(30) NOT NULL,
    Beds int(11) NOT NULL,
    bedType varchar(8) NOT NULL,
    maxOcc int(11) NOT NULL,
    basePrice DECIMAL(6,2) NOT NULL,
    decor varchar(20) NOT NULL,
    UNIQUE (RoomName)
     */

    String RoomCode;
    String RoomName;
    int Beds;
    String bedType;
    int maxOcc;
    BigDecimal basePrice;
    String decor;

}
