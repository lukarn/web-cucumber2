package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class BasePage extends Page {

    @Override
    public boolean isAt(){
        return this.bestBody.isDisplayed();
    }



    @FindBy(id = "bestBody")
    private WebElement bestBody;

    @FindBy(css = "button.foundation-style.button.foundationButton[type='submit'][value='Login']")
    private WebElement zalogujButton;

    @FindBy(id = "login_section_btn")
    private WebElement loginButton;

    @FindBy(id = "registeredPlayerLogin")
    private WebElement loginInput;

    @FindAll({
            @FindBy(css = "form > input.required.valid[name='password']"),
            @FindBy(css = "form#bestForm > input[name='password']")
    })
    private WebElement passwordInput;




    public BasePage(WebDriver driver)
    {
        super(driver);
    }

    public void setZalogujButton()
    {
        clickElement(this.zalogujButton);
        new MainPage(driver);
    }

    public BasePage setLoginButton()
    {
        try {
            clickElement(this.loginButton);
        }catch (Exception e){
            System.out.println(">>>>>>> LOGIN button not displayed");
        }
        return this;
    }

    public BasePage setLoginInput(String text)
    {
        clickElement(this.loginInput);
        this.loginInput.clear();
        this.loginInput.sendKeys(text);
        return this;
    }

    public void setPasswordInput(String text)
    {
        clickElement(this.passwordInput);
        this.passwordInput.clear();
        this.passwordInput.sendKeys(text);
    }



}
