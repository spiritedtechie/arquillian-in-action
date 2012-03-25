package com.makingdevsimple.vehicleservice.domain;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleJAXBTest {

    @BeforeClass
    public static void setUpBeforeAll() {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Test
    public void testVehicleMarshall() throws Exception {

        final Vehicle vehicle = VehicleBuilder.aVehicle().build();

        final Writer marshalledXmlWriter = marshallUsingJAXB(vehicle);

        System.out.print("Marshalled Vehicle: " + marshalledXmlWriter.toString());

        final Reader marshalledXmlReader = new StringReader(marshalledXmlWriter.toString());
        final Reader expectedXmlReader = new FileReader("src/test/resources/test-data/expected_marshall_vehicle.xml");
        assertXmlSimilar(marshalledXmlReader, expectedXmlReader);
    }

    private Writer marshallUsingJAXB(final Vehicle vehicle) throws JAXBException {

        final Writer marshalledXmlWriter = new StringWriter();
        final JAXBContext context = JAXBContext.newInstance(Vehicle.class);
        context.createMarshaller().marshal(vehicle, marshalledXmlWriter);
        return marshalledXmlWriter;
    }

    private void assertXmlSimilar(final Reader marshalledXmlReader, final Reader expectedXmlReader) throws Exception {

        final Diff diff = new Diff(expectedXmlReader, marshalledXmlReader);

        assertTrue("test XML matches expected vehicle xml " + diff, diff.similar());
    }

}
