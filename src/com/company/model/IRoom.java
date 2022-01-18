package com.company.model;

public interface IRoom {
    String getRoomNumber();
    Double getRoomPrice();
    RoomType getRoomType();
    boolean isFree();
}
