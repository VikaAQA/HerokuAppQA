import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Проверка кол-ва элементов на странице
    @Test
    public void checkAddRemoveElements() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        List<WebElement> deletebuttons = driver.findElements(By.xpath("//button[text()='Delete']"));//создаем коллекцию для проверки отображения кол-ва кнопок
        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(deletebuttons.size(), 2);//проверка отображения двух кнопок
        deletebuttons.get(1).click();//кликаем на вторую кнопку
        List<WebElement> deleteButtonsAfterDelete = driver.findElements(By.xpath("//button[text()='Delete']"));//создаем коллекцию для проверки отображения кол-ва кнопок
        softAssert.assertEquals(deleteButtonsAfterDelete.size(), 1);//проверка отображения  кол-ва кнопок
        softAssert.assertAll();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
