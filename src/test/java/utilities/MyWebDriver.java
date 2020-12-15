package utilities;

import java.net.InetAddress;
import java.security.InvalidParameterException;

public class MyWebDriver {

    public void driverConf(DriverConfiguration configuration) {
        if (configuration == null) {
            throw new InvalidParameterException("Can not set driver configuration");
        }

        configuration.setConfiguration();
    }

    public void getLocalHost() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println(">>>>>>>>>>Your current IP address : " + ip.getHostAddress());
            System.out.println(">>>>>>>>>>Your current Hostname : " + ip.getHostName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
