package com.company.model;

import java.util.Date;

/** reservation class
 * @author oge
 * @param
 *
 */
public class Reservation {
    Customer customer;
    IRoom iRoom;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.iRoom = iRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", iRoom=" + iRoom +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
