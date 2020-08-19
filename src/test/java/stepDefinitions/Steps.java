package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.MainPage;
import pages.TrainPage;
import pages.WorkPage;
import utilities.DriverManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class Steps {

    //from Jenkins
//    private String envLoginLogin = System.getenv("LOGIN_LOGIN");
//    private String envLoginPassword = System.getenv("LOGIN_PASSWORD");
//    private int envTimeoutIsAt = Integer.parseInt(System.getenv("TEST_TIMEOUT"));
    private String actualBrowser;

    //from here
    private String envLoginLogin = "sledzik";
    private String envLoginPassword = "h@rdh@rd";
    private int envTimeoutIsAt = 60;

    private static WebDriver driver;

    // Page Objects
    private BasePage basePage;
    private MainPage mainPage;
    private WorkPage workPage;
    private TrainPage trainPage;

    // Take screenshots
    private void takeScreenshot() {
        TakesScreenshot ts;
        ts = (TakesScreenshot) driver;

        if (ts != null) {
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                Date date = new Date();

                //ScreenShot
                FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/screenShots/OK " + dateFormat.format(date) + ".png"));
                System.out.println("Screenshot saved: " + System.getProperty("user.dir") + "/screenShots/OK " + dateFormat.format(date) + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No driver - no photo.");
        }

    }

    @Before
    public void beginOfTestSuite(){
        System.out.println("=============================TEST=BEGIN=====================================");
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("==============================================");
        System.out.println("=Test suite parameters(env. variables)       =");
        System.out.println("==============================================");
        System.out.println("=envLoginLogin: " + envLoginLogin);
        System.out.println("=envLoginPassword: " + envLoginPassword);
        System.out.println("=timeoutIsAt: " + envTimeoutIsAt);
        System.out.println("==============================================");
    }


    @Given("^Open the \"(.*)\" and launch the \"(.*)\"$")
    public void open_browser_and_launch_the_application(String browser,String basePageUrl)
    {
        System.out.println("============================================================================");
        System.out.println("=This Step open the " + browser + " and launch the " + basePageUrl + ".");

        // setup driver
        DriverManager driverManager = new DriverManager(driver);
        //usingBrowser;
        driver = driverManager.getDriver(browser);

        //get to base page (from data provider)
        driver.get(basePageUrl);

        // Page Object - assign
        basePage = new BasePage(driver);
        mainPage = new MainPage(driver);
        workPage = new WorkPage(driver);
        trainPage = new TrainPage(driver);

        if(browser.equalsIgnoreCase("chrome")) {
            actualBrowser = "chrome";
            takeScreenshot();
        }else if(browser.equalsIgnoreCase("firefox")){
            actualBrowser = "firefox";
        }else{
            System.out.println("Browser not recognized [NOK]");
            actualBrowser = "not-recognized";
        }



    }

    @When("^Enter the Username and Password$")
    public void enter_the_Username_and_Password()
    {
        System.out.println("============================================================================");
        System.out.println("This step enter the Username and Password on the login page.");

        basePage.setLoginButton()
                .setLoginInput(envLoginLogin)
                .setPasswordInput(envLoginPassword);

        if(actualBrowser.equalsIgnoreCase("chrome")) {
            takeScreenshot();
        }


    }

    @Then("^Click Zaloguj button$")
    public void clickZalogujButton()
    {
        System.out.println("============================================================================");
        System.out.println("This step click on the Zaloguj button.");

        basePage.setZalogujButton();

        assertTrue("----------Log in fail - you are not on MainPage", mainPage.isAt(envTimeoutIsAt));

        if(actualBrowser.equalsIgnoreCase("chrome")) {
            takeScreenshot();
        }


        if(driver != null){
            driver.quit();
        }else{
            System.out.println("Something is wrong ---> driver = null in AfterMethod");
        }

    }

//    //////////////////////
//    @When("^Login correct$")
//    public void loginCorrect()
//    {
//        System.out.println("============================================================================");
//        System.out.println("Enter username, password and login correct.");
//
//        basePage.setLoginButton()
//                .setLoginInput(envLoginLogin)
//                .setPasswordInput(envLoginPassword);
//
//        basePage.setZalogujButton();
//
//        if(actualBrowser.equalsIgnoreCase("chrome")) {
//            takeScreenshot();
//        }
//
//
//    }
//
//    @Then("^Click Work button or check work result if user worked today$")
//    public void checkWorkActivity()
//    {
//        System.out.println("============================================================================");
//        System.out.println("This step clicks on the Work button (when user did not work today) or only checks work results (if user has worked already)");
//
//
//        try {
//            mainPage.setWorkTaskButton();
//        }
//        catch (Exception e)
//        {
//            mainPage.setMenuMyPlacesButton()
//                    .setMenuWorkButton();
//        }
//
//        assertTrue("----------Error - you are not on WorkPage", workPage.isAt(envTimeoutIsAt));
//
//        if(!workPage.workCheck())
//        {
//            workPage.setWorkButton();
//            assertTrue("----------Error - you are not on WorkPage", workPage.isAt(envTimeoutIsAt));
//        }
//
//        assertTrue("----------Error - Can not read production results after work", workPage.workCheck());
//
//
//
//        if(actualBrowser.equalsIgnoreCase("chrome")) {
//            takeScreenshot();
//        }
//
//
//    }
//
/////////////////////////////////
//
//    @Then("^Click Train button or check train result if user trained today$")
//    public void checkTrainActivity()
//    {
//        System.out.println("============================================================================");
//        System.out.println("This step clicks on the Train button (when user did not work today) or only checks train results (if user has trained already)");
//
//
//        try {
//            mainPage.setTrainTaskButton();
//        }
//        catch (Exception e)
//        {
//            mainPage.setMenuMyPlacesButton()
//                    .setMenuTrainButton();
//        }
//
//
//        assertTrue("----------Error - you are not on TrainPage", trainPage.isAt(envTimeoutIsAt));
//
//
//
//        if(!trainPage.trainCheck())
//        {
//            trainPage.setTrainButton();
//            assertTrue("----------Error - you are not on TrainPage", trainPage.isAt(envTimeoutIsAt));
//        }
//
//        assertTrue("----------Error - Can not read train countdown after training", trainPage.trainCheck());
//
//        if(actualBrowser.equalsIgnoreCase("chrome")) {
//            takeScreenshot();
//        }
//
//
//
//    }
//
//
//
//    @After
//    public static void endOfTestSuite()
//    {
//        if(driver != null){
//            driver.quit();
//        }else{
//            System.out.println("Something is wrong ---> driver = null in AfterMethod");
//        }
//        System.out.println("============================================================================");
//        System.out.println("============================================================================");
//        System.out.println("=============================TEST=END=======================================");
//    }

}