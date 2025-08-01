import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Проверка элементов дропдоуна
    @Test
    public void checkDropDown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDownElement = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropDownElement);//Получение всех элементов из дропдауна
        List<WebElement> options = select.getOptions();//Добавление элементов в коллекцию

        Assert.assertEquals(options.get(0).getText(), "Please select an option");
        Assert.assertEquals(options.get(1).getText(), "Option 1");
        Assert.assertEquals(options.get(2).getText(), "Option 2");
        select.selectByVisibleText("Option 1");
        Assert.assertTrue(select.getFirstSelectedOption().isSelected());

    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}

