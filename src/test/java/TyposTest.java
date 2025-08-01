import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Проверка орфорграфии на странице
    @Test
    public void checkTypos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        SoftAssert softAssert = new SoftAssert();
        String expectext = "Sometimes you'll see a typo, other times you won,t.";
        for (int i = 1; i < 10; i++) {
            driver.navigate().refresh();
            String actualText = driver.findElement(By.xpath("//div/p[2]")).getText();
            softAssert.assertEquals(actualText, expectext);
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}

