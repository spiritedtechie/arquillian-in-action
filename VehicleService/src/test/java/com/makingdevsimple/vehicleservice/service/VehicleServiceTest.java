package com.makingdevsimple.vehicleservice.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.makingdevsimple.vehicleservice.database.VehicleInProcessDatabase;
import com.makingdevsimple.vehicleservice.domain.Vehicle;

public class VehicleServiceTest {

    private static final String REG_NO = "A123BCD";

    private VehicleInProcessDatabase database;

    private EntityManager em;

    private VehicleService service;

    @Before
    public void setUp() throws Exception {

        database = new VehicleInProcessDatabase();
        database.createTables();
        database.insertSingleVehicle();

        em =
                Persistence.createEntityManagerFactory("com.makingdevsimple.vehicleservice",
                        database.getEntityManagerProperties()).createEntityManager();
        service = new VehicleServiceImpl(em);

        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    public void testFindVehicleByRegistrationNumber() throws Exception {

        final Vehicle vehicleFound = service.findVehicleByRegistration(REG_NO);

        assertThat("Null vehicle", vehicleFound, is(notNullValue()));
        assertThat("Registration number", vehicleFound.getRegistrationNumber(), is(REG_NO));
    }

    @Test
    public void testFindVehicleByRegistrationNumberWhereDoesNotExist() {

        final Vehicle vehicleFound = service.findVehicleByRegistration("ABC");

        assertThat("Vehicle", vehicleFound, is(nullValue()));
    }

}
