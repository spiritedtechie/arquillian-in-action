package com.makingdevsimple.vehicleservice.finder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.makingdevsimple.vehicleservice.domain.Vehicle;

@Component
public class VehicleFinderServiceImpl implements VehicleFinderService {

    @PersistenceContext
    private EntityManager em;

    public VehicleFinderServiceImpl() {

    }

    public VehicleFinderServiceImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Vehicle findVehicleByRegistration(final String registrationNumber) {

        return em.find(Vehicle.class, registrationNumber);
    }

}
