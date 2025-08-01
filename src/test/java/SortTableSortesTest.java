import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class SortTableSortesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTables() {
        driver.get("https://the-internet.herokuapp.com/tables");
        SoftAssert softAssert = new SoftAssert();
        String elementName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[1]/td[1]")).getText();
        String elementMail = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[3]/td[3]")).getText();
        String elementPrice = driver.findElement(By.xpath("//table[@id='table2']/tbody/tr[2]/td[4]")).getText();
        softAssert.assertEquals(elementName, "Smith");
        softAssert.assertEquals(elementMail, "jdoe@hotmail.com");
        softAssert.assertEquals(elementPrice, "$51.00");
        softAssert.assertAll();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}

