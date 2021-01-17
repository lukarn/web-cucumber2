package utilities;


import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;


public class DriverManager {

    private WebDriver driver;

    public DriverManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebDriver getDriver(String usingBrowser)
    {
        new LocalhostData().showInfo();

        switch (usingBrowser){
            case "chrome":
                driver = (new ChromeDriver()).getChromeDriver();
                break;

            case "firefox":
                driver = (new FirefoxDriver()).getFirefoxDriver();
                break;

            default:
                fail("---------Can not launch browser: " + usingBrowser + " check parameter name or browser implementation.");
        }

        return driver;
    }




}
