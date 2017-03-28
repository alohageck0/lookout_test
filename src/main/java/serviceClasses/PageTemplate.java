package serviceClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageTemplate extends Template {
   public PageTemplate(WebDriver driver) {
      super(driver);
      PageFactory.initElements(driver, this);

   }

   @Override
   public RemoteWebDriver getDriver() {
      return (RemoteWebDriver) super.getDriver();
   }
}
