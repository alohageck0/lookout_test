package objectsRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import serviceClasses.PageTemplate;

/**
 * Created by qolsys-v on 3/6/17.
 */
public class MyLoopsPage extends PageTemplate {
    public MyLoopsPage(WebDriver driver) {
        super(driver);
    }

    private By tabTitle = By.xpath(".//*[@id='page-header-strip']/div/div/div[1]/h1");

    public By getTabTitleLocator() {
        return tabTitle;
    }

    public WebElement getTabTitle() {
        return getDriver().findElement(tabTitle);
    }

}
