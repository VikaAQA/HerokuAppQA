import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkNotification() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        WebElement clickElement = driver.findElement(By.xpath("//a[text()='Click here']"));
        clickElement.click();
        WebElement notificationElement = driver.findElement(By.xpath("//div[@id='flash-messages']/div"));
        Assert.assertEquals(notificationElement, "Action successful");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}

