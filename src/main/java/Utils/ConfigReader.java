package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String[] getIpList(String ip) {
        String ipList = properties.getProperty(ip);
        return ipList != null ? ipList.split(",") : new String[0];
    }

    public static String getBasePath(String path) {
        return properties.getProperty(path);
    }
}
