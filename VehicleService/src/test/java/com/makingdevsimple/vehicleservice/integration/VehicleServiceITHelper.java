package com.makingdevsimple.vehicleservice.integration;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class VehicleServiceITHelper {

    public static VehicleResponse executeHttpCallToFindVehicle(final String regNo) throws Exception {

        final HttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpget = new HttpGet("http://localhost:8080/vehicle-service/vehicle/" + regNo);
        final HttpResponse response = httpClient.execute(httpget);

        return new VehicleResponse(response.getStatusLine().getStatusCode(), IOUtils.toString(response.getEntity()
                .getContent()));
    }

}
