package com.makingdevsimple.vehicleservice.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.makingdevsimple.vehicleservice.VehicleService;
import com.makingdevsimple.vehicleservice.database.VehicleInProcessDatabase;
import com.makingdevsimple.vehicleservice.domain.Vehicle;
import com.makingdevsimple.vehicleservice.exception.VehicleNotFoundException;

/**
 * To test that spring application context wires up correctly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "classpath:test-applicationContext.xml" })
public class VehicleServiceSpringIntegrationTest {

    private VehicleInProcessDatabase database;

    @Autowired
    private VehicleService service;

    @Before
    public void setUp() throws Exception {

        database = new VehicleInProcessDatabase();
        database.createTables();
        database.insertSingleVehicle();
    }

    @Test
    public void testVehicleServiceWiring() throws Exception {

        final Vehicle vehicle = service.getVehicleByRegistration("A123BCD");
        assertThat("Vehicle", vehicle, is(notNullValue()));
    }

    @Test(expected = VehicleNotFoundException.class)
    public void testVehicleService_NoVehicle() throws Exception {

        service.getVehicleByRegistration("V456JAY");
    }

}
