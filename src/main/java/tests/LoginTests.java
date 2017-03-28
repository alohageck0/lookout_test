package tests;


import data.MySQLDataProviders;
import objectsRepo.HomePage;
import objectsRepo.MyLoopsPage;
import objectsRepo.SignInPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import serviceClasses.TestTemplate;

/**
 * LoginTests class used to store automated test cases.
 * <p>
 * It is used for test cases related with login functionality.
 *
 * @author Evgenii Iavorovich evgenii.iavorovich@qolsys.com
 */
public class LoginTests extends TestTemplate {
   public LoginTests(RemoteWebDriver webDriver) {
      super(webDriver);
   }

   public LoginTests() {
   }


   /**
    * Login test case with valid credentials.
    */
   @Test(groups = {"login", "smoke"}, dataProvider = "usernames", dataProviderClass = MySQLDataProviders.class, dependsOnMethods = {"clickSignIn", "enterCredentials"})
   public void loginValidPassword(String username, String password) {
      logger.info("loginValidPassword test case started");
      enterCredentials(username, password);

      SignInPage signInPage = new SignInPage(getWebDriver());
      signInPage.getSignInButton().click();
      logger.info("Sign in button clicked");

      MyLoopsPage loopsPage = new MyLoopsPage(getWebDriver());
      logger.info("Wait until page loaded");
      getWait().until(ExpectedConditions.presenceOfElementLocated(loopsPage.getTabTitleLocator()));
      logger.info("My loops page loaded");

      Assert.assertEquals(loopsPage.getTabTitle().getText(), "Loops");
      logger.info("My Loops page asserted");
   }

   /**
    * Login test case with invalid password
    */
   @Test(groups = {"login", "smoke"}, dataProvider = "invalidUser", dataProviderClass = MySQLDataProviders.class, dependsOnMethods = {"clickSignIn", "enterCredentials"})
   public void loginIncorrectEmail(String username, String password) {
      logger.info("loginInvalidPassword test case started");
      enterCredentials(username, password);

      SignInPage signInPage = new SignInPage(getWebDriver());
      signInPage.getSignInButton().click();
      logger.info("Sign in button clicked");
      Assert.assertEquals(signInPage.getErrorMessage().getText(), "Incorrect email/password combination.");
      logger.info("Error message asserted");
   }

   /**
    * Enter credentials test case
    */
   @Test(groups = {"login", "smoke"}, dataProvider = "usernames", dataProviderClass = MySQLDataProviders.class, dependsOnMethods = {"clickSignIn"})
   public void enterCredentials(String username, String password) {
      logger.info("enterCredentials test case started");
      clickSignIn();

      SignInPage signInPage = new SignInPage(getWebDriver());
      signInPage.getUsernameTextfield().sendKeys(username);
      logger.info("Username " + username + " entered");

      signInPage.getPasswordTextfield().sendKeys(password);
      logger.info("Password entered");
   }

   /**
    * Click sign in button test case
    */
   @Test(groups = {"login", "smoke"})
   public void clickSignIn() {
      logger.info("clickSignIn test case started");
      getWebDriver().navigate().to("https://dotloop.com/");
      logger.info("Home page opened");

      HomePage homePage = new HomePage(getWebDriver());
      homePage.getSignIn().click();
      logger.info("Sign in clicked");

      SignInPage signInPage = new SignInPage(getWebDriver());
      Assert.assertEquals(signInPage.getLoginPageTitle().getText(), "Welcome to dotloop.");
      logger.info("Sign in page opened");
   }
   /**
    * Click sign in button test case - broken locator
    */
   @Test(groups = {"login", "smoke"})
   public void clickSignInBroken() {
      logger.info("clickSignInBroken test case started");
      getWebDriver().navigate().to("https://dotloop.com/");
      logger.info("Home page opened");

      HomePage homePage = new HomePage(getWebDriver());
      homePage.getSignInBroken().click();
      logger.info("Sign in clicked");

      SignInPage signInPage = new SignInPage(getWebDriver());
      Assert.assertEquals(signInPage.getLoginPageTitle().getText(), "Welcome to dotloop.");
      logger.info("Sign in page opened");
   }
}
