package utilities;

import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.Assert.fail;

@NoArgsConstructor
public class FirefoxDriver {

    private WebDriver firefoxDriver;

    public WebDriver getFirefoxDriver()
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
            firefoxDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (Exception e) {
            e.printStackTrace();
            fail("---------Problem during firefox init in DriverManager.java");
        }

        System.out.println("Starting with firefox...");

        return firefoxDriver;
    }
}
