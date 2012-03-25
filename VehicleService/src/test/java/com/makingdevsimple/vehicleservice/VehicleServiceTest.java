package com.makingdevsimple.vehicleservice;

import static com.makingdevsimple.vehicleservice.domain.VehicleBuilder.aVehicle;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.text.StringContains.containsString;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.providers.DocumentProvider;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.makingdevsimple.vehicleservice.finder.VehicleFinderService;

@RunWith(JMock.class)
public class VehicleServiceTest {

    private VehicleService service;

    private Mockery mockery;

    private VehicleFinderService finderService;

    private Dispatcher dispatcher;

    @Before
    public void setupService() {

        mockery = new JUnit4Mockery();
        finderService = mockery.mock(VehicleFinderService.class);

        service = new VehicleServiceImpl(finderService);

        dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addSingletonResource(service);
        dispatcher.getProviderFactory().addMessageBodyWriter(DocumentProvider.class);
        dispatcher.getProviderFactory().addExceptionMapper(new InvalidParameterExceptionMapper());
        dispatcher.getProviderFactory().addExceptionMapper(new VechicleNotFoundExceptionMapper());
    }

    @Test
    public void testGetVehicle_InvalidRegistrationNumberSupplied() throws Exception {

        final MockHttpResponse response = getVehicleWithRegistrationNumber("AAA");

        assertThat("Response code", response.getStatus(), is(412));
        assertThat("Response message", response.getContentAsString(), is("Invalid registration number supplied"));
    }

    @Test
    public void testGetVehicle_NotNull() throws Exception {

        final String regNo = "V123JAY";

        expectVehicleFinderToReturnVehicle(regNo);

        final MockHttpResponse response = getVehicleWithRegistrationNumber(regNo);

        final String contentAsString = response.getContentAsString();
        System.out.println("Vehicle: " + contentAsString);

        assertThat("Vehicle", contentAsString, is(notNullValue()));
    }

    @Test
    public void testGetVehicle_RegistrationNumberIsCorrect() throws Exception {

        final String regNo = "V123JAY";

        expectVehicleFinderToReturnVehicle(regNo);

        final MockHttpResponse response = getVehicleWithRegistrationNumber(regNo);

        final String contentAsString = response.getContentAsString();
        System.out.println("Vehicle: " + contentAsString);

        assertThat("Vehicle reg no", contentAsString, containsString("<registrationNumber>" + regNo
                + "</registrationNumber>"));
    }

    @Test
    public void testGetVehicle_RegistrationNumberIsCorrect2() throws Exception {

        final String regNo = "V456JAY";

        expectVehicleFinderToReturnVehicle(regNo);

        final MockHttpResponse response = getVehicleWithRegistrationNumber(regNo);

        final String contentAsString = response.getContentAsString();
        System.out.println("Vehicle: " + contentAsString);

        assertThat("Vehicle reg no", contentAsString, containsString("<registrationNumber>" + regNo
                + "</registrationNumber>"));
    }

    @Test
    public void testGetVehicle_NotFound() throws Exception {

        final String regNo = "V456JAY";

        mockery.checking(new Expectations() {
            {
                oneOf(finderService).findVehicleByRegistration(with(regNo));
                will(returnValue(null));
            }
        });

        final MockHttpResponse response = getVehicleWithRegistrationNumber(regNo);

        assertThat("Vehicle reg no", response.getStatus(), is(404));
    }

    private void expectVehicleFinderToReturnVehicle(final String regNo) {
        mockery.checking(new Expectations() {
            {
                oneOf(finderService).findVehicleByRegistration(with(regNo));
                will(returnValue(aVehicle().withRegistrationNumber(regNo).build()));
            }
        });
    }

    private MockHttpResponse getVehicleWithRegistrationNumber(final String regNo) throws URISyntaxException {
        final MockHttpRequest httpRequest = MockHttpRequest.get("/vehicle/" + regNo);
        final MockHttpResponse response = new MockHttpResponse();

        dispatcher.invoke(httpRequest, response);
        return response;
    }

}
