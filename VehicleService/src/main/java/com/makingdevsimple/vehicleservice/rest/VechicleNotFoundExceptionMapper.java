package com.makingdevsimple.vehicleservice.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.makingdevsimple.vehicleservice.exception.VehicleNotFoundException;

@Component
@Provider
public class VechicleNotFoundExceptionMapper implements ExceptionMapper<VehicleNotFoundException> {

    @Override
    public Response toResponse(final VehicleNotFoundException e) {
        return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }

}
