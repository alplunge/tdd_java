package com.ap.isbtools.builders;

public class FamilyCar extends Car {
    private final boolean spareWheel;

    public static class Builder extends Car.Builder<Builder> {
        private boolean spareWheel = false;

        public Builder spareWheel() {
            spareWheel = true;
            return this;
        }

        @Override
        public FamilyCar build() {
            return new FamilyCar(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private FamilyCar(Builder builder) {
        super(builder);
        spareWheel = builder.spareWheel;
    }

    public boolean isSpareWheel() {
        return spareWheel;
    }
}
