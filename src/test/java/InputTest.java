import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Проверка ввода значений в поле
    @Test
    public void checkInput() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement element = driver.findElement(By.tagName("input"));
        SoftAssert softAssert = new SoftAssert();
        element.sendKeys("Hello");
        softAssert.assertEquals(element.getText(), "", "Поле принимает буквенные значения ");
        element.clear();
        element.sendKeys("123456789");
        softAssert.assertEquals(element.getAttribute("value"), "123456789", "Поле не принимает цифровые значения ");
        element.clear();
        element.sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(1, 1);
        softAssert.assertAll();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}

