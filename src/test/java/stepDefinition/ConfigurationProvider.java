package stepDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProvider {

    private static final ConfigurationProvider _instance = new ConfigurationProvider();
    Properties properties = new Properties();

    protected ConfigurationProvider() {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream("application.properties");
        try {
            properties.load(resourceStream);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationProvider getInstance() {
        return _instance;
    }

    public String getUrl() {
        String url = properties.getProperty("api.baseUrl");
        return url;
    }
}