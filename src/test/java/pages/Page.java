package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class Page {

    static WebDriver driver;
    private static WebDriverWait wait;
    Page(WebDriver driver)
    {
        Page.driver = driver;
        Page.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isAt();
    public boolean isAt(long timeout){
        try{
            await().atMost(timeout, TimeUnit.SECONDS).ignoreExceptions().until(this::isAt);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    void clickElement(WebElement clickLocator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(clickLocator));

        String myTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.print(myTime + " Clicking locator: " + clickLocator + " on page with title: " + driver.getTitle() );

        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0);");
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,1000);");
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0);");

        for(int i=0; i<100; i++) {
            try {
                Thread.sleep(100);
                clickLocator.click();
                i=100;
            } catch (Exception ignored) {
            }
        }

        System.out.println(" [OK]");
    }






}
