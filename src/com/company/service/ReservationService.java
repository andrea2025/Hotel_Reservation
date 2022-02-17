package com.company.service;

import com.company.model.customer.Customer;
import com.company.model.room.IRoom;
import com.company.model.reservation.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static final ReservationService SINGLETON = new ReservationService();
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();
    private static final int RECOMMENDED_ROOMS_PLUS_DAYS = 7;

    private ReservationService() {
    }

    public static ReservationService getSingleton() {
        return SINGLETON;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        final Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservations = getCustomersReservation(customer);
        if (customerReservations == null) {
            customerReservations = new LinkedList<>();
        }
        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);
        return reservation;
    }

    public Collection<IRoom> findRoom(Date checkInDate, Date checkOutDate) {
        return findAvailableRoom(checkInDate, checkOutDate);

    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }


    public Collection<IRoom> findAvailableRoom(Date checkInDate, Date checkOutDate) {
        final Collection<Reservation> allReservation = getAllReservation();
        final Collection<IRoom> notAvailableRooms = new LinkedList<>();

        for (Reservation reservation : allReservation) {
            if (reservationOverlaps(reservation, checkInDate, checkOutDate)) {
                notAvailableRooms.add(reservation.getiRoom());
            }
        }
        return rooms.values().stream().filter(room -> notAvailableRooms.stream().noneMatch(notAvailableRoom ->
                notAvailableRooms.equals(room))).collect(Collectors.toList());
    }

    private boolean reservationOverlaps(final Reservation reservation, final Date checkInDate,
                                        final Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }

    public Collection<IRoom> findAlternativeRoom(Date checkInDate, Date checkOutDate) {
        return findAvailableRoom(addPlusDays(checkInDate), addPlusDays(checkOutDate));
    }

    public Date addPlusDays(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, RECOMMENDED_ROOMS_PLUS_DAYS);

        return calendar.getTime();
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {

        return reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        final Collection<Reservation> reservations = getAllReservation();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation + "\n");
            }
        }

    }

    private Collection<Reservation> getAllReservation() {
        final Collection<Reservation> allReservation = new LinkedList<>();

        for (Collection<Reservation> reservations : reservations.values()) {
            allReservation.addAll(reservations);
        }
        return allReservation;
    }


}
