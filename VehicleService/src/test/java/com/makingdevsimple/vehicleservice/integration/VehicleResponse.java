package com.makingdevsimple.vehicleservice.integration;

class VehicleResponse {

    private final int status;

    private final String vehicleXml;

    public VehicleResponse(final int status, final String vehicleXml) {
        this.status = status;
        this.vehicleXml = vehicleXml;
    }

    public int getStatus() {
        return status;
    }

    public String getVehicleXml() {
        return vehicleXml;
    }
}