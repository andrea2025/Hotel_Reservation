package com.company.api;

import com.company.model.customer.Customer;
import com.company.model.room.IRoom;
import com.company.service.CustomerService;
import com.company.service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    public static final AdminResource SINGLETON = new AdminResource();
    public final ReservationService reservationService = ReservationService.getSingleton();
    public final CustomerService customerService = CustomerService.getSingleton();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static AdminResource getSingleton() {
        return SINGLETON;
    }


    public void addRoom(List<IRoom> rooms){
        rooms.forEach(reservationService::addRoom);
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservation(){
        reservationService.printAllReservation();
    }
}
