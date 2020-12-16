package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.MainPage;
import pages.TrainPage;
import pages.WorkPage;
import utilities.DriverManager;

import static org.junit.Assert.*;


public class Steps implements EnvironmentVariables {

    private static WebDriver driver;

    // Page Objects
    private BasePage basePage;
    private MainPage mainPage;
    private WorkPage workPage;
    private TrainPage trainPage;

    @Before
    public void beginOfTestSuite(){
        System.out.println();
        System.out.println("=============================TEST=BEGIN=====================================");
        System.out.println("============================================================================");
        System.out.println("============================================================================");
    }


    @Given("Open the {string} and launch {string}")
    public void openTheAndLaunch(String browser, String basePageUrl) {

        // setup driver
        DriverManager driverManager = new DriverManager(driver);
        //usingBrowser;
        driver = driverManager.getDriver(browser);

        //get to base page (from data provider)
        driver.get(basePageUrl);

        // Page Object - assign
        basePage = new BasePage(driver, TEST_TIMEOUT);
        mainPage = new MainPage(driver, TEST_TIMEOUT);
        workPage = new WorkPage(driver, TEST_TIMEOUT);
        trainPage = new TrainPage(driver, TEST_TIMEOUT);

    }


    @When("Click Login button if it shows up")
    public void clickLoginButtonIfItShowsUp() {
        basePage.setLoginButton();
    }

    @And("Enter the Username")
    public void enterTheUsername() {
        basePage.setLoginInput(LOGIN_LOGIN);
    }

    @And("Enter the Password")
    public void enterThePassword() {
        basePage.setPasswordInput(LOGIN_PASSWORD);
    }

    @And("Click Zaloguj button")
    public void clickZalogujButton() {
        basePage.setZalogujButton();
    }

    @Then("Check if user is logged in")
    public void checkIfUserIsLoggedIn() {
        assertTrue("----------Log in fail - you are not on MainPage", mainPage.isAt(TEST_TIMEOUT));
    }

    ///////////////////////////////////////////////////////////////////

    @And("Login correct")
    public void loginCorrect() {
        basePage.setLoginButton()
                .setLoginInput(LOGIN_LOGIN)
                .setPasswordInput(LOGIN_PASSWORD)
                .setZalogujButton();

        assertTrue("----------Log in fail - you are not on MainPage", mainPage.isAt(TEST_TIMEOUT));
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

        assertTrue("----------Error - you are not on WorkPage", workPage.isAt(TEST_TIMEOUT));

        if(!workPage.workCheck())
        {
            workPage.setWorkButton();
            assertTrue("----------Error - you are not on WorkPage", workPage.isAt(TEST_TIMEOUT));
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

        assertTrue("----------Error - you are not on TrainPage", trainPage.isAt(TEST_TIMEOUT));

        if(!trainPage.trainCheck())
        {
            trainPage.setTrainButton();
            assertTrue("----------Error - you are not on TrainPage", trainPage.isAt(TEST_TIMEOUT));
        }
    }

    @Then("Check train results")
    public void checkTrainResults() {

                assertTrue("----------Error - Can not read train countdown after training", trainPage.trainCheck());

    }



    @After
    public static void endOfTestSuite(Scenario scenario)
    {
        CommonMethods.printTestScenarioStatus(scenario);

        CommonMethods.takeScreenShotIfNOK(scenario, driver);

        CommonMethods.driverQuit(driver);
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("=============================TEST=END=======================================");
        System.out.println();
    }



}