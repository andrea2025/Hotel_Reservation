package com.company.api;

import com.company.model.customer.Customer;
import com.company.model.room.IRoom;
import com.company.model.reservation.Reservation;
import com.company.service.CustomerService;
import com.company.service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class HotelResource {
    public static final HotelResource SINGLETON = new HotelResource();
    public static final CustomerService customerService = CustomerService.getSingleton();
    public static final ReservationService reservationService = ReservationService.getSingleton();

    private HotelResource(){}

    public static HotelResource getSingleton(){
        return SINGLETON;
    }

    public Customer getCustomer(String email) {

        return customerService.getCustomer(email);
    }

    public void createACustomer(String email,String firstName,String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }

    public IRoom  getRoom(String roomNumber){
     return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveRoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail){
        final Customer customer = getCustomer(customerEmail);

        if (customer == null){
            return Collections.emptyList();
        }
        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn,Date checkOut){
        return reservationService.findRoom(checkIn,checkOut);
    }

    public Collection<IRoom> findAlternativeRooms(final Date checkIn, final Date checkOut) {
        return reservationService.findAlternativeRoom(checkIn, checkOut);
    }
    public Date addDefaultDays(final Date date) {
        return reservationService.addPlusDays(date);
    }
}
