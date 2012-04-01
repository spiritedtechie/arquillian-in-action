package com.makingdevsimple.vehicleservice.rest;

import com.makingdevsimple.vehicleservice.domain.Vehicle;
import com.makingdevsimple.vehicleservice.exception.VehicleNotFoundException;

public interface VehicleResource {

    Vehicle getVehicleByRegistration(String registrationNumber) throws VehicleNotFoundException;

}
