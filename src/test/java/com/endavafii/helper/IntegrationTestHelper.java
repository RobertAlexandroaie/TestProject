package com.endavafii.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.stream.IDataSetProducer;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public final class IntegrationTestHelper {

    public static final String RESOURCE_PREFIX = "resources://";

    private IntegrationTestHelper() {}

    public static synchronized IDataSet getOrderedDataSet(IDatabaseConnection databaseConnection) throws Exception {
        return new FilteredDataSet(new DatabaseSequenceFilter(databaseConnection), databaseConnection.createDataSet());
    }

    public static IDatabaseConnection getConnection(final Connection jdbcConnection, final boolean qualifiedTables, final String schema)
            throws DatabaseUnitException {
		DatabaseConnection connection = new DatabaseConnection(jdbcConnection,
				schema);
		connection.getConfig().setProperty(
				DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, qualifiedTables);
		connection.getConfig().setProperty(
				DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Oracle10DataTypeFactory());
		connection.getConfig().setProperty(
				DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES, true);
		return connection;
    }

    public static IDataSet getDataSet(String dataSetFile, final boolean isResource) throws Exception {
        InputStream dataSetStream = getInputStream(dataSetFile, isResource);
        IDataSetProducer producer = new FlatXmlProducer(new InputSource(dataSetStream), new EntityResolver() {

            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                if (systemId != null && systemId.startsWith(RESOURCE_PREFIX)) {
                    InputStream dtdStream = getInputStream(systemId.replace(RESOURCE_PREFIX, ""), true);
                    return new InputSource(dtdStream);
                }
                return null;
            }
        });
        /*
         * NOTE: use StreamingDataSet for very large data sets
         * IDataSet loadedDataSet = new StreamingDataSet(producer);
         */
        IDataSet loadedDataSet = new CachedDataSet(producer);
        return loadedDataSet;
    }

    private static InputStream getInputStream(String fileName, boolean isResource) throws IOException {
        InputStream returnStream;
        if (isResource) {
            returnStream = IntegrationTestHelper.class.getResourceAsStream(fileName);
            if (returnStream == null) {
                throw new IOException("Could not read file from class path: " + fileName);
            }
        } else {
            returnStream = new FileInputStream(fileName);
        }
        return returnStream;
    }
}

