package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {

    @Override
    public boolean isAt(){
        return this.fightTaskButton.isDisplayed();
    }


    @FindBy(css = "#taskButtonFight>a")
    private WebElement fightTaskButton;

    @FindBy(css = "#taskButtonTrain>a")
    private WebElement trainTaskButton;

    @FindBy(css = "#taskButtonWork>a")
    private WebElement workTaskButton;

    @FindBy(css = "a#myPlaces")
    private WebElement menuMyPlacesButton;

    @FindBy(css = "a[href*='work']")
    private WebElement menuWorkButton;

    @FindBy(css = "a[href*='train']")
    private WebElement menuTrainButton;



    public MainPage(WebDriver driver, int wait)
    {
        super(driver, wait);
    }

    public void setTrainTaskButton()
    {
        clickElement(this.trainTaskButton);
    }

    public void setWorkTaskButton()
    {
        clickElement(this.workTaskButton);
    }

    public MainPage setMenuMyPlacesButton()
    {
        clickElement(this.menuMyPlacesButton);
        return this;
    }

    public void setMenuWorkButton()
    {
        clickElement(this.menuWorkButton);
    }

    public void setMenuTrainButton()
    {
        clickElement(this.menuTrainButton);
    }

}
