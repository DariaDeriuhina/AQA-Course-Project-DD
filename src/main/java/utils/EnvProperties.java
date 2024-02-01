package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.util.Objects.isNull;

public class EnvProperties {
    public static final String PATH_TO_RESOURCES = "src/main/resources/";
    public static final String PROPERTIES_FILE_NAME = "env.properties";
    public static String BASE_URL;
    public static String API_URL;
    public static String API_TOKEN;
    public static String API_USERNAME;

    public static void setUpInstance(){
        BASE_URL = propertyValue(PATH_TO_RESOURCES, PROPERTIES_FILE_NAME, "baseUrl");
        API_URL = propertyValue(PATH_TO_RESOURCES, PROPERTIES_FILE_NAME, "apiUrl");
        API_USERNAME = propertyValue(PATH_TO_RESOURCES, PROPERTIES_FILE_NAME, "username");
        API_TOKEN = propertyValue(PATH_TO_RESOURCES, PROPERTIES_FILE_NAME, "token");
    }

    public static String propertyValue(String pathToFile, String propertyFileName, String propertyName) {
        String systemProperty = System.getProperty(propertyName);
        return !isNull(systemProperty) ? systemProperty : getPropertyValue(pathToFile, propertyFileName, propertyName);
    }

    private static String getPropertyValue(String pathToFile, String fileName, String propertyName) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(pathToFile + fileName)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(propertyName);
    }
}
