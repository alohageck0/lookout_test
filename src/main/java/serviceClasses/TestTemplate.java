package serviceClasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * TestTemplate class is a parent class for all Test case classes.
 * <p>
 * It contains most feilds and defines @BeforeSuite, @BeforeMethod, @AfterMethod
 * as well as other preparations for test runs.
 *
 * @author Evgenii Iavorovich evgenii.iavorovich@qolsys.com
 */
public class TestTemplate {
    private File appDir = new File("src");
    private MyConfig config = new MyConfig();
    private RemoteWebDriver webDriver;
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    private Properties properties = new Properties();
    private WebDriverWait wait;

    public TestTemplate(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public TestTemplate() {
    }

    /**
     * Method setups Test suite preconditions - server arguments for Appium instance,
     * starts server programmatically and configures logger properties
     *
     * @throws MalformedURLException
     */
    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() throws MalformedURLException {
        PropertyConfigurator.configure(new File(appDir, "log4j.properties").getAbsolutePath());
    }

    /**
     * Method setups test case preconditions - sets Desired capabilities for Android driver,
     * creates driver for Selenium grid using address from grid.properties,
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        File homedir = new File(System.getProperty("user.home"));
        properties.load(new BufferedInputStream(new FileInputStream(homedir + "/Automation/grid.properties")));
        logger.info("Selenium grid server address: " + properties.getProperty("serveraddress"));
        this.webDriver = new RemoteWebDriver(new URL(properties.getProperty("serveraddress")), config.getFirefoxCapabilities());
        getWebDriver().manage().window().maximize();
        this.wait = new WebDriverWait(getWebDriver(), 30);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        try {
            webDriver.quit();
            logger.info("webdriver quit");
        } catch (NullPointerException e) {
            logger.info("No webDriver");
        }
    }

    public MyConfig getConfig() {
        return config;
    }

    public RemoteWebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Properties getProperties() {
        return properties;
    }
}
