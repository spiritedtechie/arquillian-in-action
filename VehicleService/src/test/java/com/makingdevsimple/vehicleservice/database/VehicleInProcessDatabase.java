package com.makingdevsimple.vehicleservice.database;

import java.io.IOException;
import java.util.Properties;

public class VehicleInProcessDatabase extends VehicleDatabase {

    public VehicleInProcessDatabase() throws Exception {
        super();
    }

    @Override
    protected Properties getJdbcProperties() {
        final Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("database/inprocess/database.properties"));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load properties");
        }
        return props;
    }

}
