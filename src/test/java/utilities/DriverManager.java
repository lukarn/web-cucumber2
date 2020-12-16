package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;

public class DriverManager {

    private WebDriver driver;

    public DriverManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebDriver getDriver(String usingBrowser)
    {
        getLocalHost();

        switch (usingBrowser){
            case "chrome":
                driver = getChromeDriver();
                break;

            case "firefox":
                driver = getFirefoxDriver();
                break;

            default:
                fail("---------Can not launch browser: " + usingBrowser + " check parameter name or browser implementation.");
        }

        return driver;
    }

    private void getLocalHost() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println(">>>>>>>>>>Your current IP address : " + ip.getHostAddress());
            System.out.println(">>>>>>>>>>Your current Hostname : " + ip.getHostName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private WebDriver getChromeDriver()
    {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "/screenShots");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1480");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");


        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
            fail("---------Problem during chrome init in DriverManager.java");
        }

        System.out.println("Starting with chrome...");

        return driver;
    }

    private WebDriver getFirefoxDriver()
    {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile myProfile = new FirefoxProfile();
        myProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        myProfile.setPreference("pdfjs.disabled", true);
        myProfile.setPreference("browser.download.folderList", 2);
        myProfile.setPreference("browser.download.manager.showWhenStarting", false);
        myProfile.setPreference("browser.download.dir", System.getProperty("user.dir") + "/screenShots");
        options.setProfile(myProfile);

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        options.setHeadless(true);

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
            fail("---------Problem during firefox init in DriverManager.java");
        }

        System.out.println("Starting with firefox...");

        return driver;
    }




}
