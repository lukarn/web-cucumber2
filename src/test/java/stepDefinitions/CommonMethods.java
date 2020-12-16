package stepDefinitions;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class CommonMethods {

    public static void printTestScenarioStatus(Scenario scenario){
        System.out.println("============================================================================");
        System.out.println("====================================STATUS==================================");
        System.out.println("=scenario.getStatus() >>>>>>>" + scenario.getStatus() + "<<<<<<<");
        System.out.println("=scenario.isFailed() >>>>>>>" + scenario.isFailed() + "<<<<<<<");
        System.out.println("=scenario.getName() >>>>>>>" + scenario.getName() + "<<<<<<<");
        System.out.println("=scenario.getSourceTagNames() >>>>>>>" + scenario.getSourceTagNames() + "<<<<<<<");

    }

    private static String getSimpleTagString(Scenario scenario) {
        return Objects.toString(scenario.getSourceTagNames()).replace("@","").replace("[","").replace("]","").replace(" ","").replace(","," ");
    }

    public static void takeScreenShotIfNOK(Scenario scenario, WebDriver driver){
        if(scenario.isFailed() || scenario.getStatus().toString().equalsIgnoreCase("UNDEFINED")) {
            takeScreenshot(getSimpleTagString(scenario), driver);
        }
    }


    // Take screenshots
    private static void takeScreenshot(String tags, WebDriver driver) {
        TakesScreenshot ts;
        ts = (TakesScreenshot) driver;

        if (ts != null) {
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            try {
                //ScreenShot
                FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/screenShots/" + "NOK" + getDateOrTime("yyyy-MM-dd HH-mm-ss", 0) + tags + ".png"));
                System.out.println("Screenshot saved: " + System.getProperty("user.dir") + "/screenShots/" + "NOK" + getDateOrTime("yyyy-MM-dd HH-mm-ss", 0) + tags + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No driver - no photo.");
        }

    }


    //  "yyyy-MM-dd HH-mm-ss"
    public static String getDateOrTime(String patternSimpleDateFormat, int daysToAdd){
        String dt = new SimpleDateFormat(patternSimpleDateFormat).format(Calendar.getInstance().getTime());

        SimpleDateFormat sdf = new SimpleDateFormat(patternSimpleDateFormat);
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.add(Calendar.DATE, daysToAdd);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date

        return dt;

    }

    public static void driverQuit(WebDriver driver){
        if(driver != null){
            driver.quit();
        }else{
            System.out.println("Something is wrong ---> driver = null in AfterMethod");
        }
    }

}
