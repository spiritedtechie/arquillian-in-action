package com.makingdevsimple.vehicleservice;

import com.makingdevsimple.vehicleservice.domain.Vehicle;
import com.makingdevsimple.vehicleservice.exception.VehicleNotFoundException;

public interface VehicleService {

    Vehicle getVehicleByRegistration(String registrationNumber) throws VehicleNotFoundException;

}
