package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainPage extends Page {

    @Override
    public boolean isAt(){
        return this.activatePushesButton.isDisplayed();
    }


    @FindBy(css = "#activatePushes")
    private WebElement activatePushesButton;

    @FindBy(css = "button#trainButton")
    private WebElement trainButton;

    @FindBy(css = ".timeCountdown.greenFont")
    private WebElement timeCountdown;


    public TrainPage(WebDriver driver)
    {
        super(driver);
    }


    public void setTrainButton()
    {
        clickElement(this.trainButton);
    }


    private String getTimeCountdown()
    {
        return this.timeCountdown.getText();
    }

    public boolean trainCheck()
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(this.timeCountdown));
            System.out.println("Next train : " + getTimeCountdown() + " [OK]");
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
