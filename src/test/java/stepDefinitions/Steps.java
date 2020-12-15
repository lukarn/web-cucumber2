package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
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
import utilities.Chrome;
import utilities.MyWebDriver;
import utilities.DriverManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.*;


public class Steps {

    //from Jenkins
    private final String envLoginLogin = System.getenv("LOGIN_LOGIN");
    private final String envLoginPassword = System.getenv("LOGIN_PASSWORD");
    private final int envTimeoutIsAt = Integer.parseInt(System.getenv("TEST_TIMEOUT"));

    //from here
//    private final String envLoginLogin = "sledzik";
//    private final String envLoginPassword = "h@rdh@rd";
//    private final int envTimeoutIsAt = 60;

    private static WebDriver driver;

    private MyWebDriver myWebDriver;

    // Page Objects
    private BasePage basePage;
    private MainPage mainPage;
    private WorkPage workPage;
    private TrainPage trainPage;

    // Take screenshots
    private static void takeScreenshot(String status, String tags) {
        TakesScreenshot ts;
        ts = (TakesScreenshot) driver;

        if (ts != null) {
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                Date date = new Date();

                //ScreenShot
                FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/screenShots/" + status + dateFormat.format(date) + tags + ".png"));
                System.out.println("Screenshot saved: " + System.getProperty("user.dir") + "/screenShots/" + status + dateFormat.format(date) + tags + ".png");
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


    @Given("Open the {string} and launch {string}")
    public void openTheAndLaunch(String browser, String basePageUrl) {


        myWebDriver = new MyWebDriver();
        Chrome chrome = new Chrome(driver);
        myWebDriver.driverConf(chrome);

        driver = chrome.getDriver();



//        // setup driver
//        DriverManager driverManager = new DriverManager(driver);
//        //usingBrowser;
//        driver = driverManager.getDriver(browser);

        //get to base page (from data provider)
        driver.get(basePageUrl);

        // Page Object - assign
        basePage = new BasePage(driver, envTimeoutIsAt);
        mainPage = new MainPage(driver, envTimeoutIsAt);
        workPage = new WorkPage(driver, envTimeoutIsAt);
        trainPage = new TrainPage(driver, envTimeoutIsAt);

    }


    @When("Click Login button if it shows up")
    public void clickLoginButtonIfItShowsUp() {
        basePage.setLoginButton();
    }

    @And("Enter the Username")
    public void enterTheUsername() {
        basePage.setLoginInput(envLoginLogin);
    }

    @And("Enter the Password")
    public void enterThePassword() {
        basePage.setPasswordInput(envLoginPassword);
    }

    @And("Click Zaloguj button")
    public void clickZalogujButton() {
        basePage.setZalogujButton();
    }

    @Then("Check if user is logged in")
    public void checkIfUserIsLoggedIn() {
        assertTrue("----------Log in fail - you are not on MainPage", mainPage.isAt(envTimeoutIsAt));
    }

    ///////////////////////////////////////////////////////////////////

    @And("Login correct")
    public void loginCorrect() {
        basePage.setLoginButton()
                .setLoginInput(envLoginLogin)
                .setPasswordInput(envLoginPassword)
                .setZalogujButton();

        assertTrue("----------Log in fail - you are not on MainPage", mainPage.isAt(envTimeoutIsAt));
    }

    @When("Click Work Button and go to work results")
    public void clickWorkButtonAndGoToWorkResults() {

        try {
            mainPage.setWorkTaskButton();
        }
        catch (Exception e)
        {
            mainPage.setMenuMyPlacesButton()
                    .setMenuWorkButton();
        }

        assertTrue("----------Error - you are not on WorkPage", workPage.isAt(envTimeoutIsAt));

        if(!workPage.workCheck())
        {
            workPage.setWorkButton();
            assertTrue("----------Error - you are not on WorkPage", workPage.isAt(envTimeoutIsAt));
        }
    }

    @Then("Check work results")
    public void checkWorkResults() {

        assertTrue("----------Error - Can not read production results after work", workPage.workCheck());

    }

    ////////////////////////////////////////////////////////////////
    @When("Click Train button and go to train results")
    public void clickTrainButtonAndGoToTrainResults() {
        try {
            mainPage.setTrainTaskButton();
        }
        catch (Exception e)
        {
            mainPage.setMenuMyPlacesButton()
                    .setMenuTrainButton();
        }

        assertTrue("----------Error - you are not on TrainPage", trainPage.isAt(envTimeoutIsAt));

        if(!trainPage.trainCheck())
        {
            trainPage.setTrainButton();
            assertTrue("----------Error - you are not on TrainPage", trainPage.isAt(envTimeoutIsAt));
        }
    }

    @Then("Check train results")
    public void checkTrainResults() {

                assertTrue("----------Error - Can not read train countdown after training", trainPage.trainCheck());

    }



    @After
    public static void endOfTestSuite(Scenario scenario)
    {
        System.out.println("=scenario.getStatus() >>>>>>>" + scenario.getStatus() + "<<<<<<<");
        System.out.println("=scenario.isFailed() >>>>>>>" + scenario.isFailed() + "<<<<<<<");
        //System.out.println("scenario.getId() >>>>>>>" + scenario.getId() + "<<<<<<<");
        System.out.println("=scenario.getName() >>>>>>>" + scenario.getName() + "<<<<<<<");
        //System.out.println("scenario.getLine() >>>>>>>" + scenario.getLine() + "<<<<<<<");
        System.out.println("=scenario.getSourceTagNames() >>>>>>>" + scenario.getSourceTagNames() + "<<<<<<<");
        //System.out.println("scenario.getUri() >>>>>>>" + scenario.getUri() + "<<<<<<<");

        String simpleTagsString = Objects.toString(scenario.getSourceTagNames()).replace("@","").replace("[","").replace("]","").replace(" ","").replace(","," ");
        System.out.println("=Tags in simle format >>>>>>>" + simpleTagsString + "<<<<<<<");


                
        if(scenario.isFailed() || scenario.getStatus().toString().equalsIgnoreCase("UNDEFINED")) {
            takeScreenshot("NOK", simpleTagsString);
        }

        if(!scenario.isFailed() && scenario.getStatus().toString().equalsIgnoreCase("PASSED")) {
            takeScreenshot("OK", simpleTagsString);
        }



        if(driver != null){
            driver.quit();
        }else{
            System.out.println("Something is wrong ---> driver = null in AfterMethod");
        }
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("=============================TEST=END=======================================");
        System.out.println();
        System.out.println();
    }



}