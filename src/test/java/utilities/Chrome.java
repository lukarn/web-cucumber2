package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

import static org.junit.Assert.fail;

public class Chrome implements DriverConfiguration {
    private WebDriver driver;

    public Chrome(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void setConfiguration() {
        System.out.println("This is chrome!");

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

        this.driver = null;

        try {
            this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
            fail("---------Driver = null - problem during chrome init in DriverManager; check if your webdriver server is working properly and its URL address (e.g. port, versions) in DriverManager.java");
        }


    }


}
