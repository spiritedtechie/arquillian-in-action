package com.makingdevsimple.vehicleservice.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.makingdevsimple.vehicleservice.domain.Vehicle;

@Component
public class VehicleServiceImpl implements VehicleService {

    @PersistenceContext
    private EntityManager em;

    public VehicleServiceImpl() {

    }

    public VehicleServiceImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Vehicle findVehicleByRegistration(final String registrationNumber) {

        return em.find(Vehicle.class, registrationNumber);
    }

}
