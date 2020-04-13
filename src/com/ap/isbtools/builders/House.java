package com.ap.isbtools.builders;

public class House {
    private final int bedrooms;
    private final int bathrooms;
    private final int windows;
    private final int doors;
    private final int balconies;
    private final int guestRooms;

    public static final class Builder{
        private final int bedrooms;
        private final int bathrooms;
        private final int doors;

        private int windows = 0;
        private int balconies = 0;
        private int guestRooms = 0;

        public Builder(int bedrooms, int bathrooms, int doors){
            this.bedrooms = bedrooms;
            this.bathrooms = bathrooms;
            this.doors = doors;
        }

        public Builder windows(int value) {
            windows = value;
            return this;
        }

        public Builder balconies(int value) {
            balconies = value;
            return this;
        }

        public Builder guestRooms(int value) {
            guestRooms = value;
            return this;
        }

        public House build(){
            return new House(this);
        }
    }

    private House(Builder builder) {
        bedrooms = builder.bedrooms;
        bathrooms = builder.bathrooms;
        windows = builder.windows;
        doors = builder.doors;
        balconies = builder.balconies;
        guestRooms = builder.guestRooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }
}
