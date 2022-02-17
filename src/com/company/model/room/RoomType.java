package com.company.model.room;

public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");

    public String labelType;

    private RoomType(String type) {
        this.labelType = type;
    }

    public static RoomType valueType(String label){
        for(RoomType roomType:values()){
            if (roomType.labelType.equals(label)){
                return roomType;
            }
        }
        throw new IllegalArgumentException();
    }
}
