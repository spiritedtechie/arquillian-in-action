package com.makingdevsimple.vehicleservice.domain;

import java.util.Date;

public class VehicleBuilder {

    private String registrationNo = "V123JAY";

    private final String make = "Volkswagen";

    private final String model = "Golf GT TDI";

    private final String engineSize = "2.0";

    private final Date yearOfManufacture = DateUtil.dateForYear(2006);

    private final RegisteredKeeperBuilder registeredKeeper = RegisteredKeeperBuilder.aRegisteredKeeper();

    private final Date motNextDue = DateUtil.dateWithYearMonthAndDay(2012, 05, 05);

    public static VehicleBuilder aVehicle() {
        return new VehicleBuilder();
    }

    public VehicleBuilder withRegistrationNumber(final String regNo) {
        this.registrationNo = regNo;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(registrationNo, make, model, engineSize, yearOfManufacture, registeredKeeper.build(),
                motNextDue);
    }

}
