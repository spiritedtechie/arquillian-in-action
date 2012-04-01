package com.makingdevsimple.vehicleservice.service;

import com.makingdevsimple.vehicleservice.domain.Vehicle;

public interface VehicleService {

    Vehicle findVehicleByRegistration(String registrationNumber);

}
