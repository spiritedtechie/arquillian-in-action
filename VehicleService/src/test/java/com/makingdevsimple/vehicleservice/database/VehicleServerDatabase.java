package com.makingdevsimple.vehicleservice.database;

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
    protected String getDatabasePropertiesClasspathLocation() {
        return "database/hsql-server/database.properties";
    }

}
