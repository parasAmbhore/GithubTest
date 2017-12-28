package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigData {
    /**
     * reads config file and returns it as properties file
     * @return properties file
     */

    public Properties getConfigData() {

        File configFile = new File("C:\\Test\\GithHub\\TestData\\config.properties");
        Properties properties = new Properties();
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
