import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckboxTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Проверка вкл/выкл чекбокса
    @Test
    public void checkSelectCheckbox() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> element = driver.findElements(By.cssSelector("[type=checkbox]"));

        Assert.assertFalse(element.get(0).isSelected(), "Первый чек-бокс выключен ");
        element.get(0).click();
        Assert.assertTrue(element.get(0).isSelected(), "Первый чек-бокс включен");// isSelected Проверяет, выбран ли элемент (например, чек-бокс или радио-кнопка).

        Assert.assertTrue(element.get(1).isSelected(), "Второй чекбокс включен");
        element.get(1).click();
        Assert.assertFalse(element.get(1).isSelected(), "Второй чекбокс выключен");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}

