package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class LoginBasicTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();

            driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                options
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
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
