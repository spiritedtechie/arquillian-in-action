package com.makingdevsimple.vehicleservice.database;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public abstract class VehicleDatabase {

    private static final String DATASOURCE_NAME = "jdbc/VehicleDataSource";

    private final IDatabaseTester databaseTester;

    public VehicleDatabase() throws Exception {

        databaseTester = createTester();
    }

    private IDatabaseTester createTester() throws Exception {

        return new JdbcDatabaseTester(getJdbcDriver(), getJdbcUrl(), getJdbcUser(), getJdbcPassword());
    }

    public String getJdbcUrl() {
        return getJdbcProperties().getProperty("JDBC.URL");
    }

    private String getJdbcDriver() {
        return getJdbcProperties().getProperty("JDBC.DRIVER");
    }

    private String getJdbcUser() {
        return getJdbcProperties().getProperty("JDBC.USER");
    }

    private String getJdbcPassword() {
        return getJdbcProperties().getProperty("JDBC.PASSWORD");
    }

    private Properties getJdbcProperties() {
        final Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream(getDatabasePropertiesClasspathLocation()));
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load properties");
        }
        return props;
    }

    protected abstract String getDatabasePropertiesClasspathLocation();

    public Map<String, String> getEntityManagerProperties() {

        final Map<String, String> emProps = new HashMap<String, String>();
        emProps.put("javax.persistence.jdbc.driver", getJdbcDriver());
        emProps.put("javax.persistence.jdbc.url", getJdbcUrl());
        emProps.put("javax.persistence.jdbc.user", getJdbcUser());
        emProps.put("javax.persistence.jdbc.password", getJdbcPassword());

        return emProps;
    }

    public String getTomcatDataSourceContext() {

        return "<Context><Resource name=\"" + DATASOURCE_NAME + "\" auth=\"Container\" type=\"javax.sql.DataSource\" "
                + "username=\"" + getJdbcUser() + "\" password=\"" + getJdbcPassword() + "\" driverClassName=\""
                + getJdbcDriver() + "\" " + "url=\"" + getJdbcUrl() + "\" /></Context>";
    }

    public void createTables() throws Exception {

        databaseTester.getConnection().getConnection().createStatement()
                .execute(FileUtils.readFileToString(new File("src/test/resources/database/tables.sql")));
    }

    public void insertSingleVehicle() throws Exception {
        final IDataSet dataSet =
                new XmlDataSet(new FileReader("src/test/resources/database/vehicle_dbunit_data_set.xml"));

        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

}
