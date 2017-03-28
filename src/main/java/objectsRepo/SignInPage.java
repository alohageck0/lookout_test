package objectsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import serviceClasses.PageTemplate;

/**
 * Created by qolsys-v on 3/6/17.
 */
public class SignInPage extends PageTemplate {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//*[@id='forms']/div/div[2]/form/div[2]/div[1]/input")
    private WebElement usernameTextfield;

    @FindBy(xpath = ".//*[@id='forms']/div/div[2]/form/div[2]/div[2]/input")
    private WebElement passwordTextfield;

    @FindBy(xpath = ".//*[@id='forms']/div/div[2]/form/a")
    private WebElement signInButton;

    @FindBy(xpath = ".//*[@id='forms']/div/h1")
    private WebElement loginPageTitle;

    @FindBy(xpath = "html/body/section[2]/div[3]/div/p")
    private WebElement errorMessage;

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getLoginPageTitle() {
        return loginPageTitle;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getPasswordTextfield() {
        return passwordTextfield;
    }

    public WebElement getUsernameTextfield() {
        return usernameTextfield;
    }
}
