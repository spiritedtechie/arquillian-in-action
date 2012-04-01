package com.makingdevsimple.vehicleservice.database;

public class VehicleInProcessDatabase extends VehicleDatabase {

    public VehicleInProcessDatabase() throws Exception {
        super();
    }

    @Override
    protected String getDatabasePropertiesClasspathLocation() {
        return "database/hsql-inprocess/database.properties";
    }
}
