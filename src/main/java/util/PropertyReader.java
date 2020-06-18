package util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyReader {

    private static final String PROPERTIES_FILE_NAME = "app.properties";

    public static String getProperty(String propertyName) {
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            Properties properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + PROPERTIES_FILE_NAME + "' not found in the classpath");
            }
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            log.warn(e.getMessage());
            return "";
        }
    }

}
