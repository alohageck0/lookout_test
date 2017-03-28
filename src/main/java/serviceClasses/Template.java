package serviceClasses;

import org.openqa.selenium.WebDriver;

public class Template {
   private WebDriver driver;

   public Template(WebDriver driver) {
      this.driver = driver;
   }

   public WebDriver getDriver() {
      return driver;
   }


}
