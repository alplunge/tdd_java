package com.ap.isbtools.builders;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSuite {

    @Test
    public void canCreateHouseUsingBuilder() {
        House myHouse = new House.Builder(1, 1, 2).balconies(1).build();
        assertEquals(1, myHouse.getBedrooms());
    }

    @Test
    public void canCreateCityRacingCar() {
        RacingCar porsche = new RacingCar.Builder(RacingCar.Type.CITY).addCarAttribute(Car.CarAttribute.SPORT).build();
        assertEquals(RacingCar.Type.CITY, porsche.getType());
    }

    @Test
    public void canCreateFamilyCarWithSpearWheel() {
        FamilyCar fiat = new FamilyCar.Builder().addCarAttribute(Car.CarAttribute.FAMILY).spareWheel().build();
        assertEquals(true, fiat.isSpareWheel());
    }
}
