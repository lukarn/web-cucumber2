package utilities;

import java.net.InetAddress;

public class LocalhostData {

    public void showInfo() {
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
