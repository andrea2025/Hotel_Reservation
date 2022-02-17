package com.company.model.reservation;

import com.company.model.customer.Customer;
import com.company.model.room.IRoom;

import java.util.Date;
import java.util.Objects;

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

    public Customer getCustomer() {
        return customer;
    }


    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public IRoom getiRoom() {
        return iRoom;
    }

    @Override
    public String toString() {
        return "Reservation" + "\n" +
                "customer=" + customer +
                ", Room=" + iRoom +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(customer, that.customer) && Objects.equals(iRoom, that.iRoom) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, iRoom, checkInDate, checkOutDate);
    }
}
