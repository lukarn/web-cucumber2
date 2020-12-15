package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.Assert.fail;

public class Firefox implements DriverConfiguration {
    private WebDriver driver;

    public Firefox(WebDriver driver){
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
        System.out.println("This is firefox!");

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

        this.driver = null;

        try {
            this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
            fail("---------Driver = null - problem during firefox init in DriverManager");
        }


    }



}
