package com.ap.isbtools.builders;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Car {
    public enum CarAttribute { SPORT, FAMILY, BUDGET }
    final Set<CarAttribute> attributes;

    abstract static class Builder<T extends  Builder<T>> {
        EnumSet<CarAttribute> attributes = EnumSet.noneOf(CarAttribute.class);
        public T addCarAttribute(CarAttribute attribute) {
            attributes.add(Objects.requireNonNull(attribute));
            return self();
        }
        abstract Car build();

        protected abstract T self();
    }
    Car(Builder<?> builder) {
        attributes = builder.attributes.clone();
    }
}
