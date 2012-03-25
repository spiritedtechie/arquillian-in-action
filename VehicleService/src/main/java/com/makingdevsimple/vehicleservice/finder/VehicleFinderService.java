package com.makingdevsimple.vehicleservice.finder;

import com.makingdevsimple.vehicleservice.domain.Vehicle;

public interface VehicleFinderService {

    Vehicle findVehicleByRegistration(String registrationNumber);

}
