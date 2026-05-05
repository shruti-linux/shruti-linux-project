package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class LoginBasicTest {

    private final static String CHROME_DRIVER_FULL_PATH = "/path/to/chromedriver";
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        /System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FULL_PATH);
        /driver = new ChromeDriver();

        try {
            driver = new RemoteWebDriver(
                new URL("http://localhost:4444"),
                new EdgeOptions()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void login() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1350, 637));

        driver.findElement(By.cssSelector("*[data-test=\"username\"]"))
              .sendKeys("standard_user");

        driver.findElement(By.cssSelector("*[data-test=\"password\"]"))
              .sendKeys("secret_sauce");

        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]"))
              .click();

        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
}
