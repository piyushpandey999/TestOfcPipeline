package Utils;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class IpProvider {

    @DataProvider(name = "ipDataProvider")
    public Object[][] provideIps(String ip) {
        String[] ips = ConfigReader.getIpList(ip);
        Object[][] data = new Object[ips.length][1];
        for (int i = 0; i < ips.length; i++) {
            data[i][0] = ips[i];
        }
        return data;
    }
    

    // Function to read data through maven and pass to test

    @DataProvider(name = "ipDataProvider2")
    public Object[][] ipProvider() {
        String[] ips = System.getProperty("ips", "127.0.0.1").split(",");
        return Arrays.stream(ips).map(ip -> new Object[]{ip}).toArray(Object[][]::new);
    }
}
