package com.ap.isbtools.builders;

import java.util.Objects;

public class RacingCar extends Car {
    public enum Type { NASCAR, FORMULA, CITY, COLLIN }
    private final Type type;

    public static class Builder extends Car.Builder<Builder> {
        private final Type type;

        public Builder(Type type) {
            this.type = Objects.requireNonNull(type);
        }
        @Override
        public RacingCar build() {
            return new RacingCar(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
    private RacingCar(Builder builder) {
        super(builder);
        type = builder.type;
    }

    public Type getType() {
        return type;
    }
}
