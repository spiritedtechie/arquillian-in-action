package com.makingdevsimple.vehicleservice.domain;

import static com.makingdevsimple.vehicleservice.domain.DateUtil.dateWithYearMonthAndDay;

import java.util.Date;

public class RegisteredKeeperBuilder {

    private final String firstName = "Vijay";

    private final String lastName = "Patel";

    private final String houseNumber = "21";

    private final String street = "Blue Street";

    private final String area = "Area";

    private final String city = "Coventry";

    private final String postcode = "CV11VV";

    private final Date registeredFrom = dateWithYearMonthAndDay(1994, 01, 01);

    private final Date registeredTo = dateWithYearMonthAndDay(2012, 02, 05);

    public static RegisteredKeeperBuilder aRegisteredKeeper() {
        return new RegisteredKeeperBuilder();
    }

    public RegisteredKeeper build() {
        return new RegisteredKeeper(firstName, lastName, houseNumber, street, area, city, postcode, registeredFrom,
                registeredTo);
    }

}
