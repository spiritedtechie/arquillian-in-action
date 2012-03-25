package com.makingdevsimple.vehicleservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makingdevsimple.vehicleservice.domain.Vehicle;
import com.makingdevsimple.vehicleservice.exception.VehicleNotFoundException;
import com.makingdevsimple.vehicleservice.finder.VehicleFinderService;

@Path("/vehicle")
@Component
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleFinderService finderService;

    public VehicleServiceImpl() {
    }

    public VehicleServiceImpl(final VehicleFinderService finderService) {
        this.finderService = finderService;
    }

    @Override
    @GET
    @Path("/{regNo}")
    public Vehicle getVehicleByRegistration(@PathParam("regNo") final String registrationNumber)
            throws VehicleNotFoundException {

        if ("AAA".equals(registrationNumber)) {
            throw new IllegalArgumentException("Invalid registration number supplied");
        }

        final Vehicle vehicle = finderService.findVehicleByRegistration(registrationNumber);

        if (vehicle == null) {
            throw new VehicleNotFoundException();
        }

        return vehicle;
    }

}
