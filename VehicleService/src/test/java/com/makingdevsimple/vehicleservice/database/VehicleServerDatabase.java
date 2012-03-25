package com.makingdevsimple.vehicleservice.database;

import java.io.IOException;
import java.util.Properties;

public class VehicleServerDatabase extends VehicleDatabase {

    private org.hsqldb.Server server;

    public VehicleServerDatabase() throws Exception {
        super();
    }

    public void startServer() {

        server = new org.hsqldb.Server();
        server.setAddress("localhost");
        server.setDatabasePath(0, "target/hsql/testdb");
        server.setDatabaseName(0, "testdb");
        server.start();
    }

    public void stopServer() {

        if (server != null) {
            server.shutdown();
        }
    }

    @Override
    protected Properties getJdbcProperties() {
        final Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("database/server/database.properties"));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load properties");
        }
        return props;
    }

}
