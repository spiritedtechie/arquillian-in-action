package com.makingdevsimple.vehicleservice.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.StringContains.containsString;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.makingdevsimple.vehicleservice.database.VehicleServerDatabase;

/**
 * To test that application work in container (tomcat).
 */
@RunWith(Arquillian.class)
@RunAsClient
public class VehicleServiceArquillianIT {

    private static VehicleServerDatabase database;
 
    @Deployment
    public static WebArchive setupDeployment() throws Exception {

        database = new VehicleServerDatabase();
        database.startServer();

        return ShrinkWrap
                .createFromZipFile(WebArchive.class, new File("target/vehicle-service.war"))
                .addAsManifestResource(new StringAsset(database.getTomcatDataSourceContext()), "context.xml")
                .addAsLibraries(
                        DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml")
                                .artifact("org.hsqldb:hsqldb").resolveAs(GenericArchive.class));
    }

    @Before
    public void setUpDatabase() throws Exception {

        database.createTables();
        database.insertSingleVehicle();
    }

    @Test
    public void testVehicleFound() throws Exception {

        final VehicleResponse response = executeHttpCallToFindVehicle("V123JAY");

        assertThat(response.getStatus(), is(200));
        assertThat(response.getVehicleXml(), containsString("<registrationNumber>" + "V123JAY"
                + "</registrationNumber>"));
    }

    @Test
    public void testVehicleNotFound() throws Exception {

        final VehicleResponse response = executeHttpCallToFindVehicle("V456JAY");

        assertThat(response.getStatus(), is(404));
    }

    private VehicleResponse executeHttpCallToFindVehicle(final String regNo) throws Exception {

        final HttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpget = new HttpGet("http://localhost:8080/vehicle-service/vehicle/" + regNo);
        final HttpResponse response = httpClient.execute(httpget);

        return new VehicleResponse(response.getStatusLine().getStatusCode(), IOUtils.toString(response.getEntity()
                .getContent()));
    }

    private class VehicleResponse {

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
}
